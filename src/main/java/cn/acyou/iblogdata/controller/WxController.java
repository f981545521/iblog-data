package cn.acyou.iblogdata.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 微信Controller
 * @author youfang
 * @version [1.0.0, 2018-05-19 上午 10:01]
 **/
@Api
@Controller
@RequestMapping("/wx")
public class WxController {

    private static final Logger log = LoggerFactory.getLogger(WxController.class);

    private final String APP_ID = "wxd02baafe70a6e8fa";
    private final String APP_SECRET = "849e3724f3d75fa3b661cd75dd3dfa2a";
    private final String Token = "youfang";

    @RequestMapping(value = "/access",method = {RequestMethod.GET})
    @ResponseBody
    public String wxAccess(HttpServletRequest request) {
        String signature = request.getParameter("signature");//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String timestamp = request.getParameter("timestamp");//时间戳
        String nonce = request.getParameter("nonce");//随机数
        String echostr = request.getParameter("echostr");//随机字符串
        log.warn("输入参数：" + signature + "|" + timestamp + "|" + nonce + "|" + echostr);
        ArrayList<String> list = new ArrayList<>();
        list.add(nonce);
        list.add(timestamp);
        list.add(Token);
        //字典序排序
        Collections.sort(list);
        String encodeStr = DigestUtils.sha1DigestAsHex(list.get(0) + list.get(1) + list.get(2));
        log.info("生成的签名：" + signature + "计算得出的签名：" + encodeStr);
        if (encodeStr.equals(signature)){
            return echostr;
        }
        return "";
    }

}