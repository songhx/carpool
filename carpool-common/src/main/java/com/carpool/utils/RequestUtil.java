package com.carpool.utils;

import com.carpool.Global;
import org.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

/**
 * HTTP请求处理工具类
 * HTTP请求处理工具类
 *
 * @author system
 * @version 1.0
 */
public class RequestUtil {

    private static final Logger LOGGER = Logger.getLogger(RequestUtil.class.getName());

    public static final  String POST = "POST";
    public static final  String GET = "GET";
    /**
     * 将request查询参数封装至Map
     *
     * @param request  请求
     * @param printLog 是否打印日志
     * @return 参数Map
     */
    public static Map<String, Object> getParameters(HttpServletRequest request,
                                                    boolean printLog) {
        Enumeration<String> enume = request.getParameterNames();
        Map<String, Object> map = new HashMap<String, Object>();
        while (enume.hasMoreElements()) {
            String key = enume.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
            if (printLog) {
                LOGGER.info(key + "==>" + value);
            }
        }
        if (map.get(Global.SORT_ORDER) != null) {
            map.put(Global.SORT_ORDER, "asc");
        }
        return map;
    }

    /**
     * 将request查询参数封装至Map
     *
     * @param request 请求
     * @return 参数Map
     */
    public static Map<String, Object> getParameters(HttpServletRequest request) {

        return getParameters(request, false);
    }

    /**
     * 获取请求方IP
     *
     * @param request 请求
     * @return 客户端Ip
     */
    public static String getClientIp(HttpServletRequest request) {
        String xff = request.getHeader("x-forwarded-for");
        if (xff == null) {
            return request.getRemoteAddr();
        }
        return xff;
    }

    /**
     * 主要功能:获取请求方IP
     * 注意事项:无
     *
     * @param request 请求
     * @return String IP
     */
    public static String getIpAddrByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取完整的请求URL
     *
     * @param request 请求
     * @return URL
     */
    public static String getRequestUrl(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    /**
     * 主要功能:获取request
     * 注意事项:无
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 主要功能:获取前端请求并转换map
     * 注意事项:无
     *
     * @param request 请求
     * @return 参数map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getBody(HttpServletRequest request) {
        // 获取前台参数
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                }
            }
        }
        body = stringBuilder.toString();
        // 转换成json对象
        JSONObject json = new JSONObject(body);
        // 转化成map合适
        Map<String, Object> paramers = new HashMap<String, Object>();
        Iterator<String> keys = json.keys();
        String key;
        while (keys.hasNext()) {
            key = keys.next();
            paramers.put(key, json.get(key));
        }
        return paramers;
    }

    /**
     * 发送https请求
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static com.alibaba.fastjson.JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        com.alibaba.fastjson.JSONObject jsonObject = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
//            TrustManager[] tm = {new MyX509TrustManager()};
//            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//            sslContext.init(null, tm, new java.security.SecureRandom());
//            // 从上述SSLContext对象中得到SSLSocketFactory对象
//            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            //conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);

            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = com.alibaba.fastjson.JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            LOGGER.info("连接超时：{}"+ ce);
        } catch (Exception e) {
            LOGGER.info("https请求异常：{}"+e);
        }
        return jsonObject;
    }


    public static String  wxHttpsRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = new StringBuffer();
        try {
            byte[] b = outputStr.getBytes("UTF-8");
            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            httpUrlConn.setDoOutput(true);
            // 设置连接输入流为true
            httpUrlConn.setDoInput(true);
            // 设置请求方式为post
            httpUrlConn.setRequestMethod(requestMethod);
            // post请求缓存设为false
            httpUrlConn.setUseCaches(false);
            // 设置该HttpURLConnection实例是否自动执行重定向
            httpUrlConn.setInstanceFollowRedirects(true);
            httpUrlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            httpUrlConn.setRequestProperty("Content-Length", String.valueOf(b.length));
            httpUrlConn.connect();
            // 当有数据需要提交时
            if (StringUtils.isNotEmpty(outputStr)) {
                OutputStream writer = httpUrlConn.getOutputStream();
                // 发送参数
                writer.write(b);
                // 清理当前编辑器的左右缓冲区，并使缓冲区数据写入基础流
                writer.flush();
                writer.close(); // 重要且易忽略步骤 (关闭流,切记!)
            }
            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();

        } catch (ConnectException ce) {
            LOGGER.info("Weixin server connection timed out.");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("https request error:{}" + e);
        }
        return buffer.toString();
    }

}
