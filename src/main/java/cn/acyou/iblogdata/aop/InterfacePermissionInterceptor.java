package cn.acyou.iblogdata.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 接口权限控制拦截器
 * @author youfang
 * @version [1.0.0, 2019-04-26 下午 03:41]
 **/
public class InterfacePermissionInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(InterfacePermissionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) {
        logger.info("InterfacePermissionInterceptor preHandle —————————— " + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }
}
