package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.jmessage.JMessageHelper;
import cn.acyou.iblogdata.utils.Md5Util;
import cn.jmessage.api.common.model.RegisterInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-16 下午 03:33]
 **/
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{


    @RequestMapping(value = "login/{username}", method = {RequestMethod.GET})
    @ResponseBody
    public String login(HttpServletRequest request, @PathVariable String username){
        session.setAttribute("user", username);
        registerJiGuangAccount(request, session, username);
        return "恭喜你：" + username + "登录成功";
    }

    @RequestMapping(value = "demoPage", method = {RequestMethod.GET})
    public String demoPage(){
        System.out.println(request.getRequestURI());
        String flag = request.getParameter("flag");
        if ("1".equals(flag)){
            throw new ServiceException();
        }
        return "demo/demoPage";
    }




}
