package cn.acyou.iblogdata.conf.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 使用@Value注解读取properties文件
 * @author youfang
 * @version [1.0.0, 2018-08-14 下午 02:28]
 **/
@Data
@Component
public class WxProperties2 {

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.wxCallback}")
    private String wxCallback;


}
