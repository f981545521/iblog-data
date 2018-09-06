package cn.acyou.iblogdata.security;

import cn.acyou.iblogdata.security.MemberAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * spring security
 * https://docs.spring.io/spring-security/site/docs/current/reference/html/jc.html
 * @author youfang
 * @version [1.0.0, 2018-9-6 下午 08:53]
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberAuthProvider authProvider;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailHander;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.formLogin().loginPage("/login").loginProcessingUrl("/user/login").failureUrl("/login-error").permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .formLogin().loginPage("/login").loginProcessingUrl("/login/form")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailHander).permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .authorizeRequests()
                    .antMatchers("/dist/**", "/dist").permitAll()//允许所有用户都有权限访问登录页面
                    .antMatchers("/h5plus/**", "/h5plus").permitAll()
                    .antMatchers("/static/**", "/static").permitAll()
                    .antMatchers("/student/students").hasRole("ADMIN") //表示这个资源需要有ROLE_ADMIN的这个角色才能访问。不然就会提示拒绝访问
                    .anyRequest().authenticated() //任何没有匹配上的其他的url请求，只需要用户被验证
                .and()
                .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);

    }

    /**
     * 第一个版本 （弃用）
     */
    public void configureGlobalOld(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("user123").roles("USER")
                .and()
                .withUser("admin").password("admin123").roles("ADMIN")
        ;


    }
}
