package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.websocket.MyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-10 下午 05:51]
 **/
@Controller
@RequestMapping("ws")
@DependsOn("myHandler")
@Slf4j
public class SocketController extends BaseController{

    @Autowired
    private MyHandler handler;


    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public String login(Integer id) {
        session.setAttribute("userId", id);
        log.info("登录成功，userId=" + id);
        return "success";
    }

    @RequestMapping(value = "/message", method = {RequestMethod.POST})
    @ResponseBody
    public String sendMessage(Integer id, String message) {
        Integer currentId = (Integer) session.getAttribute("userId");
        message = currentId + " | 对你说：" + message;
        if (id != null){
            handler.sendMessageToUser(id, new TextMessage(message));
        }else {
            handler.sendMessageToAllUsers(new TextMessage(message));
        }
        return "发送成功";
    }

}
