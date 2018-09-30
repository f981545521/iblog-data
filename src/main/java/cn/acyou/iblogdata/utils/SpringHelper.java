package cn.acyou.iblogdata.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author youfang
 * @version [1.0.0, 2018-09-30 上午 10:39]
 **/
@Component
public class SpringHelper implements BeanFactoryPostProcessor {
    private static ConfigurableListableBeanFactory beanFactory;

    public static ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringHelper.beanFactory = beanFactory;
    }

    /**
     * 获取指定name的bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    /**
     * 获取指定type的Bean
     */
    public static <T> T getBean(Class<T> clz) throws BeansException {
        @SuppressWarnings("unchecked")
        T result = (T) beanFactory.getBean(clz);
        return result;
    }

    /**
     * 包含Bean
     * @param name beanClassName
     * @return true/false
     */
    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }
}
