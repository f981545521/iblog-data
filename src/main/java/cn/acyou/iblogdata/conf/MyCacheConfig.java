package cn.acyou.iblogdata.conf;

import cn.acyou.iblogdata.conf.j2cache.J2CacheSpringCacheManageAdapter;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.J2Cache;
import net.oschina.j2cache.J2CacheBuilder;
import net.oschina.j2cache.J2CacheConfig;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


/**
 * 使用J2Cahce 代替spring cache
 * @author youfang
 * @version [1.0.0, 2018-8-28 下午 09:06]
 **/
@Slf4j
@Configuration
@EnableCaching
public class MyCacheConfig extends CachingConfigurerSupport {

    @Override
    public CacheManager cacheManager() {
        // 引入配置
        J2CacheConfig config = null;
        try {
            config = J2CacheConfig.initFromConfig("/j2cache/j2cache.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 生成 J2CacheBuilder
        J2CacheBuilder j2CacheBuilder = J2CacheBuilder.init(config);
        // 构建适配器
        return new J2CacheSpringCacheManageAdapter(j2CacheBuilder, true);
    }

    @Bean
    public CacheChannel initCacheChannel(){
        log.info("初始化：j2cache ————————————————>");
        return J2Cache.getChannel();
    }
}