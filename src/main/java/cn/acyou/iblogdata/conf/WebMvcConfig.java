package cn.acyou.iblogdata.conf;

import cn.acyou.iblogdata.aop.InterfacePermissionInterceptor;
import cn.acyou.iblogdata.aop.SessionInterceptor;
import cn.acyou.iblogdata.aop.SpringMvcInterceptor;
import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.exception.UnLoginException;
import cn.acyou.iblogdata.utils.ResultInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

/**
 *
 */
@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 根据类型注入一个List，只要Spring中有这个类型的都加到这个集合中
     */
    @Autowired(required = false)
    private List<HandlerInterceptor> interceptorList = Collections.emptyList();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /* Session Interceptor */
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");
        /* 接口权限 Interceptor */
        registry.addInterceptor(new InterfacePermissionInterceptor()).addPathPatterns("/**").excludePathPatterns("*.js");
        for (HandlerInterceptor handlerInterceptor : interceptorList){
            registry.addInterceptor(handlerInterceptor);
        }
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*//**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/dist/**").addResourceLocations("classpath:/dist/");
        registry.addResourceHandler("/h5plus/**").addResourceLocations("classpath:/h5plus/");
    }


    /**
     * springboot拦截器提取@Value属性值时为空的解决方案。
     * interceptor默认是不被spring context掌管的。所以还添加@bean ，加入的spring 容器下，才可以读取的spring容器内的变量值
     * 通过Profile注解可以实现，除了某个环境加载Bean的问题
     *
     * Bean 的加载问题：
     * Application启动后会自动扫描同级以及子级目录中的注解自动装备bean，需要加上@SpringBootApplication。
     * 如果使用mybatis，在dao层接口上使用@Repository是扫描不出来的，需要使用@Mapper才行。
     */
    @Bean
    @Profile("!dev")
    public SpringMvcInterceptor springMvcInterceptor() {
        return new SpringMvcInterceptor();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        for (URLMappingEnums enums: URLMappingEnums.values()){
            registry.addViewController(enums.getPath()).setViewName(enums.getPage());
        }
    }

    /**
     *  使用CORS解决解决跨域问题
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT");
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver(){
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
                ResultInfo resultInfo = new ResultInfo();
                ModelAndView mv = new ModelAndView("error");
                Throwable t = Throwables.getRootCause(e);
                log.error("统一异常处理，" + e.getMessage());
                e.printStackTrace();
                if (t instanceof ServiceException){
                    resultInfo.setCode(400);
                    resultInfo.setMessage(t.getMessage());
                }else
                if (t instanceof FileUploadBase.SizeLimitExceededException){
                    resultInfo.setCode(503);
                    resultInfo.setMessage("文件大小超过限制");
                } else
                if (t instanceof UnLoginException){
                    if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
                        response.setHeader("REDIRECT", "REDIRECT");//告诉ajax我是重定向
                        //告诉ajax我重定向的路径
                        response.setHeader("CONTENTPATH", "/login");
                        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
                    }else {
                        try {
                            response.sendRedirect("/login");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }else{
                    e.printStackTrace();
                    resultInfo.setCode(400);
                    resultInfo.setMessage("喔呦，程序奔溃咯！");
                }
                //响应Ajax请求
                if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
                    responseResult(response, resultInfo);

                    //这里无论如何都要抛出ModelAndView 的，否则报错：
                    // getWriter() has already been called for this response
                    return mv;
                }
                mv.addObject("code", resultInfo.getCode());
                mv.addObject("message", resultInfo.getMessage());
                String requestUrl = request.getRequestURL().toString();
                mv.addObject("requestUrl", requestUrl);
                //打印堆栈日志到字符串
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                e.printStackTrace(new java.io.PrintWriter(buf, true));
                String expMessage = buf.toString();
                String expSimpleMessage =  (expMessage.length() > 500) ? expMessage.substring(0, 500):expMessage;
                try {
                    buf.close();
                } catch (IOException ex) {
                    e.printStackTrace();
                }
                mv.addObject("expMessage", expSimpleMessage);
                return mv;//响应ModelAndView请求
            }
        });
    }

    private void responseResult(HttpServletResponse response, ResultInfo result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        response.setStatus(200);
        try {
            PrintWriter pw = response.getWriter();
            pw.write(JSON.toJSONString(result));
            pw.flush();
            pw.close();
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(createFastJsonHttpMessageConverter());
    }

    private FastJsonHttpMessageConverter createFastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullNumberAsZero);
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        return converter;
    }


}
