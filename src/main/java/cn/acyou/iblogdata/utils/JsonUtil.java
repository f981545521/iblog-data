package cn.acyou.iblogdata.utils;

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




}
