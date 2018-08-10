package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.entity.jmessage.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-10 上午 09:40]
 **/
@Controller
@RequestMapping("im")
public class JmessageController {

    /**
     * 授权加载
     * @return 授权
     */
    @RequestMapping("auth")
    @ResponseBody
    public Auth authPlayload(){
        return new Auth();
    }
}
