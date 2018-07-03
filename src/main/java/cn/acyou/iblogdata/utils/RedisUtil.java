package cn.acyou.iblogdata.utils;

import cn.acyou.iblogdata.commons.RedisResp;
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


    /**
     * Hash get
     * @param key key
     * @param hashKey result
     * @return RedisResp
     */
    public RedisResp hGet(String key, String hashKey) {
        String strValue = (String)this.redisTemplate.opsForHash().get(key, hashKey);
        return new RedisResp(0, "SUCCESS", key, strValue);
    }

    /**
     * Hash put
     * @param key IBLOGDATA:STUDENT:INFO 文件夹
     * @param hashKey 文件夹下的key
     * @param value key 的value
     * @return RedisResp
     */
    public RedisResp hPut(String key, Object hashKey, Object value) {
        this.redisTemplate.opsForHash().put(key, hashKey, value);
        return new RedisResp(0, "SUCCESS");
    }

    /**
     * hash 删除
     * @param key IBLOGDATA:STUDENT:INFO 文件夹
     * @param hashKeys 文件夹下的key
     * @return RedisResp
     */
    public RedisResp hDel(String key, String... hashKeys) {
        Long counts = 0L;
        if (hashKeys.length > 1) {
            Object[] objKeys = new Object[hashKeys.length];
            objKeys = (Object[])hashKeys.clone();
            counts = this.redisTemplate.opsForHash().delete(key, objKeys);
        } else {
            counts = this.redisTemplate.opsForHash().delete(key, new Object[]{hashKeys[0]});
        }

        RedisResp resultBean = new RedisResp(0, "SUCCESS", counts, key);
        return resultBean;
    }


}
