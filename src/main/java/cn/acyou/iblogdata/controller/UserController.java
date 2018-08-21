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

    @Autowired
    private ThreadPoolTaskExecutor threadExecutor;

    @Autowired
    private JMessageHelper jMessageHelper;

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


    private void registerJiGuangAccount(final HttpServletRequest request, HttpSession session, String username){

        //不能在多线程中直接使用request，request会在主线程完成后销毁。这里传递的是一个副本，无法通过它获取session
        threadExecutor.execute(new Runnable() {

            private ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<HttpServletRequest>();

            @Override
            public void run() {
                System.out.println("异步执行线程——————————————————> ");
                threadLocal.set(request);
                System.out.println("将要注册：" + username);
                String secret = username + "xiaojuanniao";
                String password = Md5Util.getMD5(secret);
                System.out.println("密码：" + password);
                List<RegisterInfo> users = new ArrayList<RegisterInfo>();
                RegisterInfo user = RegisterInfo.newBuilder().setUsername(username).setPassword(password).build();
                users.add(user);
                jMessageHelper.registerUsers(request, users);
                //HttpSession httpSession = request.getSession();
                System.out.println("线程中的Session访问信息" + session.getAttribute("user"));
                System.out.println("线程中的Request访问信息 ：" + request.getRequestURL());
                HttpSession httpSession = threadLocal.get().getSession();
                System.out.println("注册第三方账号成功" + httpSession.getAttribute("user"));

            }
        });
    }

}
