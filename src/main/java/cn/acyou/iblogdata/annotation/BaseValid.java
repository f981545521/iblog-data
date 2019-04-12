package cn.acyou.iblogdata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求参数通用校验类
 *
 * @version [1.0.0, 2017年11月23日]
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface BaseValid {
    //是否可以为空, 默认不可以为空
    boolean nullable() default false;

    //最大长度
    int maxLength() default 0;

    //最小长度
    int minLength() default 0;

    //最小值，针对数字类型
    int min() default 0;

    //最大值，针对数字类型
    int max() default 0;

    //数字取值范围，针对数字类型
    String[] range() default {};

    int[] numberRange() default {};

    //提供几种常用的正则验证
    RegexType regexType() default RegexType.NONE;

    //自定义正则验证
    String regexExpression() default "";

    //参数或者字段描述,这样能够显示友好的异常信息
    String message() default "";

    //返回错误码信息
    String code() default "";

    //集合类型不能为空
    boolean notEmpty() default false;

}