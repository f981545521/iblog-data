package cn.acyou.iblogdata.utils;

import org.springframework.stereotype.Component;

/**
 * 使用@Component 注解，可以直接使用别名：${@thymeleafUtil.sayHelloTo(name)}
 * @author youfang
 * @version [1.0.0, 2018-07-27 下午 01:28]
 **/
@Component(value = "thymeleafUtil")
public class ThymeleafUtil {

    public static String sayHello(){
        return "你好";
    }

    public static String sayHelloTo(String name){
        return "你好：" + name;
    }

}
