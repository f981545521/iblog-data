package cn.acyou.iblogdata.aop;

import cn.acyou.iblogdata.utils.ResultInfo;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 针对Controller 作日志记录，输入与输出
 * 在AOP中，描述切面的术语有通知（advice），切点（pointcut），连接点（join point）。
 *
 * SpringAOP可以应用5种类型的通知：
 * 1.前置通知（Before）：在目标方法被调用之前调用通知功能。
 * 2.后置通知（After）：在目标方法完成之后调用通知，此时不会关心方法的输出是什么。
 * 3.返回通知（After-returning）：在目标方法成功执行之后调用通知。
 * 4.异常通知（After-throwing）：在目标方法抛出异常后调用通知。
 * 5.环绕通知（Around）：通知包裹了被通知的方法，在被通知的方法调用之前和调用之后执行自定义的行为。
 *
 * 连接点（join point）
 * 是在应用执行过程中能够插入切面的一个点。这个点可以是调用方法时、抛出异常时、甚至修改一个字段时。切面代码可以利用这些点插入到应用的正常流程之中，并添加新的行为。
 *
 * 切点（pointcut）
 * 一个切面并不需要通知应用的所有连接点。切点有助于缩小切面所通知的连接点范围。
 * 切点的定义会匹配通知所要织入的一个或多个连接点。
 * 我们通常使用明确的类和方法名称，或是利用正则表达式定义所匹配的类和方法名称来指定这些切点。
 * 稍后会介绍切入点表达式。另外，有些AOP框架是允许我们创建动态的切点，可以根据运行时的决策（比如方法的参数值）来决定是否应用通知。
 *
 *
 * https://blog.csdn.net/zhengchao1991/article/details/53391244
 *
 *  切入点指示符用来指示切入点表达式目的，，在Spring AOP中目前只有执行方法这一个连接点，Spring AOP支持的AspectJ切入点指示符如下：
 *    execution：用于匹配方法执行的连接点；
 *    within：用于匹配指定类型内的方法执行；
 *    this：用于匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这样就可能包括引入接口也类型匹配；
 *    target：用于匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配；
 *    args：用于匹配当前执行的方法传入的参数为指定类型的执行方法；
 *  - @within：用于匹配所以持有指定注解类型内的方法；
 *  - @target：用于匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解；
 *  - @args：用于匹配当前执行的方法传入的参数持有指定注解的执行；
 *  - @annotation：用于匹配当前执行方法持有指定注解的方法；
 *    bean：Spring AOP扩展的，AspectJ没有对于指示符，用于匹配特定名称的Bean对象的执行方法；
 *    reference pointcut：表示引用其他命名切入点，只有@ApectJ风格支持，Schema风格不支持。
 *  AspectJ切入点支持的切入点指示符还有： call、get、set、preinitialization、staticinitialization、initialization、handler、adviceexecution、withincode、cflow、cflowbelow、if、@this、@withincode；但Spring AOP目前不支持这些指示符，使用这些指示符将抛出IllegalArgumentException异常。这些指示符Spring AOP可能会在以后进行扩展。
 * @author youfang
 * @version [1.0.0, 2018-08-24 下午 10:05]
 **/
@Slf4j
@Aspect
@Component
public class LogAspect {

    /**
     * 定义一个方法, 用于声明切入点表达式. 一般地, 该方法中再不需要添入其他的代码.
     * 使用 @Pointcut 来声明切入点表达式.
     * 后面的其他通知直接使用方法名来引用当前的切入点表达式.
     *
     * Controller 的rest 请求
     */
    @Pointcut("execution(* cn.acyou.iblogdata.controller.*.*(..)) && (@annotation(org.springframework.web.bind.annotation.ResponseBody) || @target(org.springframework.web.bind.annotation.RestController) )")
    public void performance(){

    }

    @Before("performance()")
    public void before(JoinPoint jp){
        String clazzName = jp.getSignature().getDeclaringType().getSimpleName();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        log.info("[{}]|{}|{}", "begin\t", clazzName + "." + methodName, JSON.toJSONString(args));
    }

    @AfterReturning(pointcut = "performance()", returning = "result")
    public void afterReturn(JoinPoint jp, Object result){
        if (result instanceof ResultInfo){
            ResultInfo resultInfo = (ResultInfo) result;
            String clazzName = jp.getSignature().getDeclaringType().getSimpleName();
            String methodName = jp.getSignature().getName();
            log.info("[{}]|{}|{}", "end\t",  clazzName + "." + methodName, JSON.toJSONString(resultInfo.getData()));
        }
    }

    @AfterThrowing(pointcut = "performance()", throwing = "t")
    public void afterReturn(JoinPoint jp, Throwable t){
        String clazzName = jp.getSignature().getDeclaringType().getSimpleName();
        String methodName = jp.getSignature().getName();
        log.info("[{}]|{}|{}", "error",  clazzName + "." + methodName, t.getMessage());
        t.printStackTrace();
    }

}
