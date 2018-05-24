package cn.acyou.iblogdata.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author youfang
 * @date 2018-05-24 下午 09:22
 */
@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean setValue(String key, String value, long time) {
        try {
            ValueOperations<String, String> valueOps =  redisTemplate.opsForValue();
            valueOps.set(key, value);
            if (time > 0) redisTemplate.expire(key, time, TimeUnit.HOURS);
            return true;
        } catch (Throwable t) {
            log.error("缓存[" + key + "]失败, value[" + value + "]", t);
        }
        return false;
    }

    public boolean setValue(String key, String value) {
        return setValue(key, value, -1);
    }

    public boolean containsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Throwable t) {
            log.error("判断缓存存在失败key[" + key + ", error[" + t + "]");
        }
        return false;
    }

    public String getValue(String key) {
        try {
            ValueOperations<String, String> valueOps =  redisTemplate.opsForValue();
            return valueOps.get(key);
        } catch (Throwable t) {
            log.error("获取缓存失败key[" + key + ", error[" + t + "]");
        }
        return null;
    }

}
