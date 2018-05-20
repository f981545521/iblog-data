package cn.acyou.iblogdata.controller;

import cn.acyou.iblog.utility.HttpClientUtil;
import cn.acyou.iblogdata.entity.AccessToken;
import cn.acyou.iblogdata.entity.UserInfo;
import cn.acyou.iblogdata.entity.UserList;
import cn.acyou.iblogdata.utils.AppConstant;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
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
