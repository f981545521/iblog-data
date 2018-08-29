package cn.acyou.iblogdata.jmx;

import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.J2Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.jmx.export.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-29 上午 09:32]
 **/

@Slf4j
@Component
@ManagedResource(objectName= "iblog:name=cacheMbean" , description= "j2cache cacheMbean Bean" )
public class CacheMBean {
    @Autowired
    private CacheManager cacheManager;

    private String name;


    @ManagedAttribute(description="j2cache cacheMbean")
    public void setName(String name) {
        this.name = name;
    }

    @ManagedAttribute()
    public String getName() {
        return name;
    }

    @ManagedOperation(description = "获取所有缓存")
    public Collection<String> getCaches() {
        Collection<String> caches = cacheManager.getCacheNames();
        for (String  cache: caches){
            log.info("缓存名称 ： " + cache);
        }
        return caches;
    }

    @ManagedOperation(description = "清除缓存")
    public String clearCache() {
        Collection<String> names = cacheManager.getCacheNames();
        for (String name : names) {
            J2Cache.getChannel().clear(name);
        }
        return "清除缓存成功";
    }

    @ManagedOperation(description = "根据name清除缓存")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "name", description = "要清除的缓存名")
    })
    public String clearCache(String name) {
        Cache cache = cacheManager.getCache(name);
        if (cache == null) {
            return "name not match!";
        } else {
            J2Cache.getChannel().clear(name);
            return "remove " + name + " success!";
        }
    }
}
