package cn.acyou.iblogdata.entity.jmessage;

import cn.acyou.iblogdata.jsms.ConfConstant;
import cn.acyou.iblogdata.utils.RandomUtil;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-10 上午 09:42]
 **/
@Data
public class Auth implements Serializable {

    private static final long serialVersionUID = 7917179325598879081L;
    private String appkey;//appkey : 开发者在极光平台注册的 IM 应用 appkey
    private String random_str;//random_str : 20-36 长度的随机字符串, 作为签名加 salt 使用
    private String signature;//signature : 签名，10 分钟后失效（只针对初始化操作，初始化成功则之后的操作跟签名无关）
    private String timestamp;//timestamp : 当前时间戳，用于防止重放攻击，精确到毫秒

    public Auth(){
        appkey = ConfConstant.appkey;
        random_str = RandomUtil.createRandomStr(20);
        timestamp = String.valueOf(new Date().getTime());
        String assemble = "appkey="+appkey+"&timestamp="+timestamp+"&random_str="+random_str+"&key=" + ConfConstant.masterSecret;
        signature = DigestUtils.md5Hex(assemble);
    }

}
