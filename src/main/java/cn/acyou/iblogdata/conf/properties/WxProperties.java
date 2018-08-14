package cn.acyou.iblogdata.conf.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 这样该对象的成员变量会被自动赋值，可以在其他地方进行注入
 *
 * prefix 用来选择哪个属性的prefix名字来绑定。
 * ignoreUnknownFields = false告诉Spring Boot在有属性不能匹配到声明的域的时候抛出异常。
 * @author youfang
 * @version [1.0.0, 2018-08-14 下午 01:21]
 **/
@Data
@Component//如果使用@Configuration就不是原来的bean了，会造成stackoverflow
@ConfigurationProperties(prefix = "wx", ignoreUnknownFields = false)
public class WxProperties {

    private String appid;

    private String wxCallback;

    private OtherInfo otherInfo;

    @Data
    public static class OtherInfo {
        private String appkey;
        private String appsecret;
    }
}

