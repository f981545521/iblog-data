package cn.acyou.iblogdata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不需要记录日志，在类/方法上加此注解
 * @author youfang
 * @version [1.0.0, 2018-08-30 下午 02:34]
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WithoutLog {
}
