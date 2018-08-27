package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.jmessage.JMessageHelper;
import cn.acyou.iblogdata.utils.Md5Util;
import cn.jmessage.api.common.model.RegisterInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpSession session;

    @Autowired
    private ThreadPoolTaskExecutor threadExecutor;

    @Autowired
    private JMessageHelper jMessageHelper;

    /**
     * 1. 服务器在接收到客户端的请求之后，会创建request对象和response对象
     * 2.服务器会通过request对象把客户的数据，包括请求信息都封装到这个对象里面
     * 3.所以我们在控制层，servlet里面就是service方法里面通过request得到很多数据，并对信息进行相应的业务处理，最后反馈给用户结果
     * 4.这个结果我们是通过response来封装的，并带到用户面前
     * 5.当这整个过程结束之后，request和response对象的周期也就结束了，他们的生命范围就是用户的一次请求和得到的一次结果的反馈。
     */
    protected void registerJiGuangAccount(final HttpServletRequest request, HttpSession session, String username){

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
