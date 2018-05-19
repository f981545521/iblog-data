package cn.acyou.iblogdata.tests;

import cn.acyou.iblogdata.controller.WxController;
import org.junit.Test;
import org.springframework.data.redis.core.script.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 微信接口测试
 * @author youfang
 * @version [1.0.0, 2018-05-19 下午 10:48]
 **/
public class WxTest {

    @Test
    public void test222() throws UnsupportedEncodingException {
        String wxUrl = "http://www.qq.com/api/wx/verification";
        String encodeURL = URLEncoder.encode(wxUrl, "UTF-8");
        System.out.println(encodeURL);
    }

    @Test
    public void testSHA1(){

        String signature = "8a5b60eb577869ec8d33af0676461a527c57e28d";
        String timestamp = "1526698659";
        String nonce =  "1156985779";
        String echostr =  "13059544329142392751";
        String token =  "youfang";

        ArrayList<String> list = new ArrayList<>();
        list.add(nonce);
        list.add(timestamp);
        list.add(token);
        //字典序排序
        Collections.sort(list);
        System.out.println(list.get(0) + list.get(1) + list.get(2));
        String encodStr = DigestUtils.sha1DigestAsHex(list.get(0) + list.get(1) + list.get(2));
        System.out.println(encodStr);
        System.out.println(signature);
    }


    @Test
    public void testWx(){
        WxController wxController = new WxController();
        System.out.println(wxController.getWxUserInfo("omM_b0VcL1AuqvQNN35o9fAYzc2g"));
    }
}
