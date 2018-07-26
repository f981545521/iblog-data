package cn.acyou.iblogdata.conf;

import cn.acyou.iblogdata.aop.SpringMvcInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(springMvcInterceptor()).addPathPatterns("/**");
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
     * @return
     */
    @Bean
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
