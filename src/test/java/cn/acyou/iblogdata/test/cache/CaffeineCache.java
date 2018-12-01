package cn.acyou.iblogdata.test.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @author youfang
 * @version [1.0.0, 2018-12-01 下午 03:05]
 **/
public class CaffeineCache {
    public static void main(String[] args) {
        Cache<String, Object> manualCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
        String key = "name";

        String obj2 = (String) manualCache.getIfPresent(key);
        System.out.println(obj2);

        String res = (String) manualCache.get(key, x -> {
            return null;
        });
        System.out.println(res);
        String res2 = (String) manualCache.get(key, x -> {
            return "ok";
        });
        System.out.println(res2);
        Object obj = manualCache.getIfPresent(key);
        System.out.println(obj);
    }
}
