package cn.acyou.iblogdata.utils;

import java.util.HashMap;
import java.util.Map;

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
}
