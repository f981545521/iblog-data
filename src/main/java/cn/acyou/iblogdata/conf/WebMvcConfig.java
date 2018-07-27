package cn.acyou.iblogdata.conf;

import cn.acyou.iblogdata.aop.SessionInterceptor;
import cn.acyou.iblogdata.aop.SpringMvcInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 根据类型注入一个List，只要Spring中有这个类型的都加到这个集合中
     */
    @Autowired(required = false)
    private List<HandlerInterceptor> interceptorList = Collections.emptyList();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");
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
}
