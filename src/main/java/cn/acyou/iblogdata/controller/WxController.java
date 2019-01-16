package cn.acyou.iblogdata.controller;

import cn.acyou.iblog.utility.HttpClientUtil;
import cn.acyou.iblogdata.entity.*;
import cn.acyou.iblogdata.constant.AppConstant;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
import java.util.UUID;

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

    private static final String APP_ID = "wxd02baafe70a6e8fa";
    private static final String APP_SECRET = "849e3724f3d75fa3b661cd75dd3dfa2a";
    private static final String TOKEN = "youfang";

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
        list.add(TOKEN);
        //字典序排序
        Collections.sort(list);
        String encodeStr = DigestUtils.sha1DigestAsHex(list.get(0) + list.get(1) + list.get(2));
        log.info("生成的签名：" + signature + "计算得出的签名：" + encodeStr);
        if (encodeStr.equals(signature)){
            return echostr;
        }
        return "";
    }

    @ApiOperation(value = "微信JSSDK配置", notes = "微信JSSDK配置")
    @RequestMapping(value = "/wxConfig", method = RequestMethod.POST)
    @ResponseBody
    public WxConfig getWxConfig(String url){
        WxConfig wxConfig = new WxConfig();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String nonceStr = UUID.randomUUID().toString().trim().replaceAll("-", ""); // 必填，生成签名的随机串
        String jsapiTicket = getJsapiTicket();
        String signature = "";
        String encryption = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
        log.warn("jsapiTicket >>>  : " + jsapiTicket);
        System.out.println("jsapiTicket >>>  : " + jsapiTicket);
        signature = DigestUtils.sha1DigestAsHex(encryption);
        wxConfig.setAppId(APP_ID);
        wxConfig.setNonceStr(nonceStr);
        wxConfig.setTimestamp(timestamp);
        wxConfig.setSignature(signature);
        return wxConfig;
    }

    /**
     * 获取jsapi_ticket
     * @return jsapi_ticket
     */
    private String getJsapiTicket(){
        //需要加缓存将ticket缓存起来
        //jsapi_ticket是公众号用于调用微信JS接口的临时票据。正常情况下，jsapi_ticket的有效期为7200秒，通过access_token来获取。由于获取jsapi_ticket的api调用次数非常有限，频繁刷新jsapi_ticket会导致api调用受限，影响自身业务，开发者必须在自己的服务全局缓存jsapi_ticket 。
        String apiUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + getAccessToken() + "&type=jsapi";
        String responseStr = HttpClientUtil.doGet(apiUrl);
        System.out.println("jsapi获取ticket" + responseStr);
        JsapiTicket ticket = JSON.parseObject(responseStr, JsapiTicket.class);
        return ticket.getTicket();
    }



    /**
     * 根据APPID & appsecret 获取accesstoken
     * @return AccessToken
     */
    private String getAccessToken(){
        String accessTokenUrl = AppConstant.WX_TOKEN + "?grant_type=client_credential&appid="+ APP_ID +"&secret=" + APP_SECRET;
        String responseStr = HttpClientUtil.doGet(accessTokenUrl);
        AccessToken accessToken = JSON.parseObject(responseStr, AccessToken.class);
        if (accessToken.getAccess_token() != null){
            return accessToken.getAccess_token();
        }else {
            return "";
        }
    }

    public UserList getUserList(String nextOpenid){
        String accessToken = getAccessToken();
        String userListUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken;
        if (StringUtils.isNotEmpty(nextOpenid)){
            userListUrl =  userListUrl + "&next_openid=" + nextOpenid;
        }
        String responseStr = HttpClientUtil.doGet(userListUrl);
        return JSON.parseObject(responseStr, UserList.class);
    }

    public UserInfo getWxUserInfo(String openId){
        String accessToken = getAccessToken();
        String getUserInfoURL = AppConstant.WX_USER_INFO2 + "?access_token=" + accessToken + "&openid="+ openId +"&lang=zh_CN";
        String responseStr = HttpClientUtil.doGet(getUserInfoURL);
        return JSON.parseObject(responseStr, UserInfo.class);
    }

}
