package cn.acyou.iblogdata.jmx;

import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-29 上午 09:32]
 **/

@Slf4j
@Component
@ManagedResource(objectName= "iblog:name=cacheMbean" , description= "j2cache cacheMbean Bean" )
public class CacheMBean {

    @Autowired
    private CacheChannel cacheChannel;

    private String name;


    @ManagedAttribute(description="j2cache cacheMbean")
    public void setName(String name) {
        this.name = name;
    }

    @ManagedAttribute()
    public String getName() {
        return name;
    }

    @ManagedOperation(description = "增加缓存成功")
    public String setCache() {
       CacheExpiredListener cacheListener = new CacheExpiredListener() {
            @Override
            public void notifyElementExpired(String s, String s1) {
                System.out.println("notifyElementExpired 通知终止");
            }
        };
        Cache cache = cacheChannel.getL1Provider().buildCache("business", cacheListener);
        cache.put("name", "asdfg");//这个数据会存在L1中，而L2中没有
        cacheChannel.set("business", "business", "Hello word!");//这个会同时存在L1与L2中
        return "success";
    }

    @ManagedOperation(description = "增加缓存")
    public String setCache2() {
        cacheChannel.set("business", "12", "Hello J2Cache");
        return "success";
    }

    @ManagedOperation(description = "根据region获取所有缓存")
    public Collection<String> getCaches(String region) {
        Collection<String> caches = cacheChannel.keys(region);
        for (String  cache: caches){
            log.info("缓存名称 ： " + cache);
        }
        return caches;
    }
    @ManagedOperation(description = "根据name获取所有region中的缓存")
    public List<String> getCaches2(String name) {
        List<String> caches = new ArrayList<>();
        Collection<CacheChannel.Region> regions = cacheChannel.regions();
        log.info("regions - " + regions.toString());
        for (CacheChannel.Region region : regions) {
            CacheObject obj = cacheChannel.get(region.getName(), name);
            log.info(obj.toString());
            caches.add(obj.toString());
        }
        return caches;
    }

    @ManagedOperation(description = "清除所有缓存")
    public String clearCache() {
        Collection<CacheChannel.Region> regions = cacheChannel.regions();
        for (CacheChannel.Region region : regions) {
            log.info("TO remove region : " + region);
            cacheChannel.clear(region.getName());
        }
        return "清除缓存成功";
    }

    @ManagedOperation(description = "根据region清除缓存")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "region", description = "要清除的缓存region")
    })
    public String clearCache(String region) {
        cacheChannel.clear(region);
        return "清除成功";
    }
}
