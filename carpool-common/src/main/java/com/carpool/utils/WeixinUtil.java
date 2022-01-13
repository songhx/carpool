package com.carpool.utils;

import com.carpool.weixin.AccessToken;
import com.carpool.weixin.Template;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 处理微信工具类
 */
public class WeixinUtil {

	/**
	 * 日志
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(WeixinUtil.class);

	private final static  String APP_ID = "wx9c1308ceb1b46994"; //app_id

	private final static  String SECRET = "APPSECRET"; //secret

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx9c1308ceb1b46994&secret=APPSECRET";

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {

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
			// 当有数据需要提交时
			if (!org.apache.commons.lang.StringUtils.isEmpty(outputStr)) {
				byte[] b = outputStr.getBytes("UTF-8");
				OutputStream writer = httpUrlConn.getOutputStream();
				// 发送参数
				writer.write(b);
				// 清理当前编辑器的左右缓冲区，并使缓冲区数据写入基础流
				writer.flush();
				writer.close(); // 重要且易忽略步骤 (关闭流,切记!)
				httpUrlConn.setRequestProperty("Content-Length", String.valueOf(b.length));
			}
			httpUrlConn.connect();
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
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			LOGGER.error("Weixin server connection timed out.");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("https request error:{}" + e);
		}
		return jsonObject;
	}
	/**
	 * 获取access_token
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequestforToken(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setAccessToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				LOGGER.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequestforToken(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
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
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			LOGGER.error("Weixin server connection timed out.");
		} catch (Exception e) {
			LOGGER.error("https request error:{}", e);
		}
		return jsonObject;
	}
    /** 
     * 接口调用  POST 
     */  
	public static JSONObject httpURLConnectionPOST(String jsCode) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		String POST_URL = "https://api.weixin.qq.com/sns/jscode2session";
		try {
			URL url = new URL(POST_URL);
			// 将url 以 open方法返回的urlConnection 连接强转为HttpURLConnection连接
			// (标识一个url所引用的远程对象连接)
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中
			// 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
			connection.setDoOutput(true);
			// 设置连接输入流为true
			connection.setDoInput(true);
			// 设置请求方式为post
			connection.setRequestMethod("POST");
			// post请求缓存设为false
			connection.setUseCaches(false);
			// 设置该HttpURLConnection实例是否自动执行重定向
			connection.setInstanceFollowRedirects(true);
			// 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
			// application/x-javascript text/xml->xml数据
			// application/x-javascript->json对象
			// application/x-www-form-urlencoded->表单数据
			// ;charset=utf-8 必须要，不然那边会出现乱码【★★★★★】
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			// 建立连接
			// (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
			connection.connect();
			// 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
			DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
			String appid = "appid=" + URLEncoder.encode("wx9c1308ceb1b46994", "utf-8"); // 已修改【改为错误数据，以免信息泄露】
			String secret = "&secret=" + URLEncoder.encode("d94a7755ecda7d6ff2533d4ca0328c67", "utf-8"); // 已修改【改为错误数据，以免信息泄露】
			String js_code = "&js_code=" + URLEncoder.encode(jsCode, "utf-8"); // 已修改【改为错误数据，以免信息泄露】
			String grant_type = "&grant_type=" + URLEncoder.encode("authorization_code", "utf-8"); // 已修改【改为错误数据，以免信息泄露】
			// 格式 parm = aaa=111&bbb=222&ccc=333&ddd=444
			String parm = appid + secret + js_code + grant_type;
			// 将参数输出到连接
			dataout.writeBytes(parm);
			// 输出完成后刷新并关闭流
			dataout.flush();
			dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
			// 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
			BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			// 循环读取流,若不到结尾处
			while ((line = bf.readLine()) != null) {
				buffer.append(line);
			}
			bf.close(); // 重要且易忽略步骤 (关闭流,切记!)
			connection.disconnect(); // 销毁连接
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("发送GET请求出现异常！" + e);
		}
		return jsonObject;
	}

	/**
	 * 发送模板信息
	 * @param token
	 * @param template
	 * @return
	 */
	public static int sendTemplateMsg(String token, Template template) {
		int errorCode = -1;
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", token);
		JSONObject jsonResult = httpRequest(requestUrl, "POST", template.toJSON().trim());
		if (jsonResult != null) {
			errorCode = jsonResult.getInt("errcode");
			String errorMessage = jsonResult.getString("errmsg");
			if (errorCode != 0) {
				LOGGER.error("模板消息发送失败:" + errorCode + "," + errorMessage);
			}
		}
		return errorCode;
	} 
}
