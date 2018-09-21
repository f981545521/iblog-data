package cn.acyou.iblogdata.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-03 下午 01:51]
 **/
public class ParamUtil {

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
     * 去除字符串中的空格、回车、换行符、制表符等
     * @param str 待替换的Str
     * @return 替换后的str
     */
    public static String replaceSpecialStr(String str) {
        String result = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            result = m.replaceAll("");
        }
        return result;
    }

}
