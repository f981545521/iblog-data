package cn.acyou.iblogdata.aop;

import cn.acyou.iblogdata.annotation.ParamValid;
import cn.acyou.iblogdata.utils.EnhanceValidUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;


@Slf4j
@Aspect
@Component
public class ParameterValidateAspect2 {

    /**
     * 定义一个方法, 用于声明切入点表达式. 一般地, 该方法中再不需要添入其他的代码.
     * 使用 @Pointcut 来声明切入点表达式.
     * 后面的其他通知直接使用方法名来引用当前的切入点表达式.
     *
     * 所有Controller 的请求
     */
    @Pointcut("execution(* cn.acyou.iblogdata.controller.*.*(..))")
    public void parameterValid(){

    }

    @Before("parameterValid()")
    public void before(JoinPoint jp){
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        Annotation[][] parameterAnnotations = methodSignature.getMethod().getParameterAnnotations();
        Object[] args = jp.getArgs();
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            int paramIndex = ArrayUtils.indexOf(parameterAnnotations, parameterAnnotation);
            for (Annotation annotation : parameterAnnotation) {
                if (annotation instanceof ParamValid) {
                    Object paramValue = args[paramIndex];
                    EnhanceValidUtil.valid(paramValue);
                }
            }
        }
    }


}
