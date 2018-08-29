package cn.acyou.iblogdata.test.cache;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;
import net.oschina.j2cache.J2CacheBuilder;
import net.oschina.j2cache.J2CacheConfig;
import org.junit.Test;

/**
 * @author youfang
 * @version [1.0.0, 2018-8-28 下午 09:15]
 **/
public class MainCacheTest {

    public static void main(String[] args) {
        CacheChannel cache = J2Cache.getChannel();
        cache.set("default", "11", "一级缓存");
        //缓存操作
        cache.set("default", "1", "Hello J2Cache");
        System.out.println(cache.get("default", "1"));
        cache.evict("default", "1");
        System.out.println(cache.get("default", "1"));

        cache.close();
    }

    @Test
    public void test1(){
        J2CacheConfig config = new J2CacheConfig();
        //填充 config 变量所需的配置信息
        J2CacheBuilder builder = J2CacheBuilder.init(config);
        CacheChannel channel = builder.getChannel();
        //进行缓存的操作
        channel.close();
    }
}
