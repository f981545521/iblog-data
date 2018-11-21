package cn.acyou.iblogdata.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-03 上午 10:59]
 **/
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    public static ObjectMapper objectMapper = new ObjectMapper();

    public JsonUtil() {

    }

    public static <T> T readValue(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (Exception var3) {
            var3.printStackTrace();
            logger.error(var3.getMessage());
            return null;
        }
    }

    public static String toJSonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception var2) {
            var2.printStackTrace();
            logger.error(var2.getMessage());
            return null;
        }
    }

    /**
     * 获取Json字符串的某个节点内容
     *
     * @param jsonString：字符串
     * @param key：节点key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String readTree(String jsonString, String key) {
        try {
            JsonNode node = objectMapper.readTree(jsonString);
            if (node == null) {
                return "";
            }
            return node.get(key).toString();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }


}
