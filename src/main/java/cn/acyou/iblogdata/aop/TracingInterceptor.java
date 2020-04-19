package cn.acyou.iblogdata.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author youfang
 * @version [1.0.0, 2020-4-19 下午 05:28]
 **/
@Slf4j
@Aspect
@Component
public class TracingInterceptor {
    public TracingInterceptor() {
        System.out.println("TracingInterceptor初始化");
    }

    @Pointcut("execution(* cn.acyou.iblogdata.service.impl.*.*(..)) ")
    public void performance() {

    }

    @Before("performance()")
    public void before(JoinPoint jp) {
        System.out.println("TracingInterceptor!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
