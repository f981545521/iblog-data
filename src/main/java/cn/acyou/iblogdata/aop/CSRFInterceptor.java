package cn.acyou.iblogdata.aop;

import cn.acyou.iblogdata.annotation.CSRFTokenRefresh;
import cn.acyou.iblogdata.annotation.CSRFTokenValidate;
import com.google.common.base.Strings;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;


/**
 * CSRF拦截
 * @author youfang
 */
//@Order(Ordered.HIGHEST_PRECEDENCE + 2)
//@Component
//@Profile("!unit-test")
public class CSRFInterceptor extends HandlerInterceptorAdapter {
    private int length = 10;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            HttpSession session = request.getSession();
            if (method.isAnnotationPresent(CSRFTokenValidate.class)) {
                CSRFTokenValidate csrfTokenValidate = method.getAnnotation(CSRFTokenValidate.class);
                if (csrfTokenValidate.value()) {
                    String headerCsrf = request.getHeader("csrf");
                    if (Strings.isNullOrEmpty(headerCsrf) || headerCsrf.length() != length) {
                        throw new RuntimeException("csrf token invalid");
                    }

                    String sessionCsrf = (String) session.getAttribute("csrf");
                    if (!headerCsrf.equals(sessionCsrf)) {
                        throw new RuntimeException("csrf token invalid");
                    }
                    session.removeAttribute("csrf");

                }

            }
            if (method.isAnnotationPresent(CSRFTokenRefresh.class)) {
                CSRFTokenRefresh csrfTokenRefresh = method.getAnnotation(CSRFTokenRefresh.class);
                if (csrfTokenRefresh.value()) {
                    String token = generateToken();
                    session.setAttribute("csrf", token);
                    response.setHeader("csrf", token);
                }
            }
        }
        return super.preHandle(request, response, handler);
    }


    private String generateToken() {
        return RandomStringUtils.random(length, true, true);
    }
}
