package com.carpool.utils;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 名称：StringUtils <br>
 * 描述：String工具类<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class StringUtils {
    public static final String EMPTY = "";
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 判断字符串是否不为空，不为空则返回true
     *
     * @param str 源数据
     * @return Boolean
     */
    public static boolean isNotEmpty(String str) {
        if (str != null && !"".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    /**
     * Object 对象转换成字符串
     * @param obj
     * @return
     */
    public static String toStringByObject(Object obj){
        return toStringByObject(obj,false,null);
    }
    /**
     * Object 对象转换成字符串，并可以根据参数去掉两端空格
     * @param obj
     * @return
     */
    public static String toStringByObject(Object obj, boolean isqdkg, String datatype){
        if(obj==null){
            return "";
        }else{
            if(isqdkg){
                return obj.toString().trim();
            }else{
                //如果有设置时间格式类型，这转换
                if(StringUtils.hasText(datatype)){
                    if(obj instanceof Timestamp){
                        return DateUtils.format((Timestamp)obj,datatype);
                    }else if(obj instanceof Date){
                        return DateUtils.format((Timestamp)obj,datatype);
                    }
                }
                return obj.toString();
            }


        }
    }
    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        } else {
            int strLen = str.length();

            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    public static boolean hasText(String str) {
        return hasText((CharSequence)str);
    }

    public static int parseInt(Object str) {
        return parseInt(str, 0);
    }

    public static int parseInt(Object str, int defaultValue) {
        if (str == null || str.equals("")) {
            return defaultValue;
        }
        String s = str.toString().trim();
        if (!s.matches("-?\\d+")) {
            return defaultValue;
        }
        return Integer.parseInt(s);
    }

    /**
     * 格式化
     * @param value
     * @param dot
     * @return
     */
    public static String NullToString(String value,String dot){
        return  org.apache.commons.lang.StringUtils.isBlank(value) ? dot : value;
    }

    /**
     *
     * @param value
     * @param dot
     * @return
     */
    public static String NullToString(Integer value,String dot){
        return value == null ? dot : String.valueOf(value);
    }


    public static String join(Object[] array, String sep) {
        return join((Object[])array, sep, (String)null);
    }

    public static String join(Collection list, String sep) {
        return join((Collection)list, sep, (String)null);
    }

    public static String join(Collection list, String sep, String prefix) {
        Object[] array = list == null?null:list.toArray();
        return join(array, sep, prefix);
    }

    public static String join(Object[] array, String sep, String prefix) {
        if(array == null) {
            return "";
        } else {
            int arraySize = array.length;
            if(arraySize == 0) {
                return "";
            } else {
                if(sep == null) {
                    sep = "";
                }

                if(prefix == null) {
                    prefix = "";
                }

                StringBuilder buf = new StringBuilder(prefix);

                for(int i = 0; i < arraySize; ++i) {
                    if(i > 0) {
                        buf.append(sep);
                    }

                    buf.append(array[i] == null?"":array[i]);
                }

                return buf.toString();
            }
        }
    }

    public static String jsonJoin(String[] array) {
        int arraySize = array.length;
        int bufSize = arraySize * (array[0].length() + 3);
        StringBuilder buf = new StringBuilder(bufSize);

        for(int i = 0; i < arraySize; ++i) {
            if(i > 0) {
                buf.append(',');
            }

            buf.append('\"');
            buf.append(array[i]);
            buf.append('\"');
        }

        return buf.toString();
    }
}
