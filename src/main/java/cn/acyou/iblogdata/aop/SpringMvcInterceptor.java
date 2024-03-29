/*
 * 文 件 名:  SpringMvcInterceptor
 * 版    权:  Copyright 2018 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0.0>
 * 创 建 人:  youfang
 * 创建时间:   2018-07-26

 */
package cn.acyou.iblogdata.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-26 下午 03:21]
 **/
@Slf4j
public class SpringMvcInterceptor extends HandlerInterceptorAdapter {
    @Value("${wx.wxCallback}")
    private String wxCallback;

    @Value("${wx.appid}")
    private String appid;

    @Autowired
    private Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            String wxCallback = environment.getProperty("wx.wxCallback");//通过environment也可以获取properties中的配置文件
            System.out.println(wxCallback);
            System.out.println("方法Handler");
            String wxCallbackUrl = "https://open.weixin.qq.com/connect/oauth2/authorize" + "?appid="+appid+"&redirect_uri="+URLEncoder.encode(wxCallback, "UTF-8") +"&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
            System.out.println("拦截器获取微信callback" + wxCallbackUrl);
        }
        if (handler instanceof ResourceHttpRequestHandler){
            System.out.println("资源文件Handler");
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
