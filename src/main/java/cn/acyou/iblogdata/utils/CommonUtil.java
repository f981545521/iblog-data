package cn.acyou.iblogdata.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-03 下午 01:51]
 **/
public class CommonUtil {

    /**
     * 从URL中提取参数Map
     * @param url 路径
     * @return 参数Map
     */
    public static Map<String, String> getQueryString(String url) {
        Map<String, String> map = new HashMap<String, String>();
        int start = url.indexOf("?");
        if (start >= 0) {
            String str = url.substring(start + 1);
            String[] paramsArr = str.split("&");
            for (String param : paramsArr) {
                String[] temp = param.split("=");
                map.put(temp[0], temp[1]);
            }
        }
        return map;
    }

    /**
     * 从URL中提取Path参数
     * @param url 路径
     * @return 参数Map
     */
    public static String getQueryStringFromPath(String url) {
        int start = url.lastIndexOf("/") + 1;
        if (url.lastIndexOf("?") == -1){
            return url.substring(start);
        }
        return url.substring(start, url.lastIndexOf("?"));
    }

    /**
     * 加密手机号
     * @param phoneNumber 12345678922
     * @return 123****8922
     */
    public static String encryptPhone(String phoneNumber){
        return phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
    }
    /**
     * 加密身份证号
     * @param idCard 身份证号
     * @return 32023****5632
     */
    public static String encryptIdCard(String idCard){
        return idCard.replaceAll("(\\d{4})\\d{10}(\\w{4})","$1****$2");
    }

    public static void main(String[] args) {
        String url = "http://localhost:8080/mkt/bargain/BA201812270950471538258?id=123";
        System.out.println(getQueryStringFromPath(url));
        String url2 = "http://localhost:8080/mkt/bargain/BA201812270950471538258";
        System.out.println(getQueryStringFromPath(url2));

    }

}
