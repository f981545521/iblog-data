package cn.acyou.iblogdata.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理登录成功的。
 * @author youfang
 * @version [1.0.0, 2018-9-6 下午 09:57]
 **/
@Component("authenticationSuccessHandler")
public class MemberAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        super.onAuthenticationSuccess(request, response, authentication);

        //这里可以根据实际情况，来确定是跳转到页面或者json格式。
        //如果是返回json格式，那么我们这么写

        Map<String,String> map=new HashMap<>();
        map.put("code", "200");
        map.put("msg", "登录成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));


        //如果是要跳转到某个页面的，比如我们的那个whoim的则
        //new DefaultRedirectStrategy().sendRedirect(request, response, "/user/whoim");
    }


}
