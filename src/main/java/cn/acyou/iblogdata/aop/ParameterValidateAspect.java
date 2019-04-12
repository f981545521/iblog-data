package cn.acyou.iblogdata.aop;

import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.so.BaseValidateEntity;
import cn.acyou.iblogdata.utils.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class ParameterValidateAspect {

    /**
     * 定义一个方法, 用于声明切入点表达式. 一般地, 该方法中再不需要添入其他的代码.
     * 使用 @Pointcut 来声明切入点表达式.
     * 后面的其他通知直接使用方法名来引用当前的切入点表达式.
     *
     * Controller 的rest 请求
     */
    @Pointcut("@annotation(cn.acyou.iblogdata.annotation.ParameterValid)")
    public void parameterValid(){

    }

    @Before("parameterValid()")
    public void before(JoinPoint jp){
        Object[] args = jp.getArgs();
        for (Object arg: args){
            if (arg instanceof BaseValidateEntity){
                String valid = ValidateUtil.valid(arg);
                if (StringUtils.isNotEmpty(valid)){
                    throw new ServiceException(valid);
                }
            }
        }
    }


}
