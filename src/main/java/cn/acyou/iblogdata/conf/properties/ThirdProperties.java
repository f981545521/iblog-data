package cn.acyou.iblogdata.conf.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author youfang
 * @version [1.0.0, 2018-8-15 下午 09:00]
 **/
@Slf4j
@Configuration
@PropertySource(value = {"classpath:third.properties"})
public class ThirdProperties {

    @Value("${im.appkey}")
    private String appkey;

    public ThirdProperties() {
        log.info("ThirdProperties初始化...");
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        log.info("ThirdProperties加载中...");
        this.appkey = appkey;
    }

    /**
     * 处理@Value注释所需的属性占位符configurer（好像不是必须的）
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
