package cn.acyou.iblogdata.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Component;

import java.beans.Introspector;

/**
 * 为什么Bean不能以两个大写字母开头
 * 1. 包扫描
 * {@link org.springframework.context.annotation.ClassPathBeanDefinitionScanner#doScan(String...)}
 * 2. 扫描获取beanName
 * <pre>
 *      String beanName = this.beanNameGenerator.generateBeanName(candidate, this.registry);
 *  </pre>
 * 3. 生成bean名称 {@link org.springframework.context.annotation.AnnotationBeanNameGenerator#generateBeanName(BeanDefinition, BeanDefinitionRegistry)}
 * {@link org.springframework.context.annotation.AnnotationBeanNameGenerator#buildDefaultBeanName(BeanDefinition)} (BeanDefinition, BeanDefinitionRegistry)}
 * 最后由方法：{@link Introspector#decapitalize(String)} 生成bean的名称
 * 为什么这么做？
 * Thus "FooBah" becomes "fooBah" and "X" becomes "x", but "URL" stays as "URL".
 *
 * @author youfang
 * @version [1.0.0, 2020-4-4 下午 09:20]
 **/
@Slf4j
@Component
public class TOkBean {

    public TOkBean() {
        System.out.println("初始化TOkBean");
    }


}
