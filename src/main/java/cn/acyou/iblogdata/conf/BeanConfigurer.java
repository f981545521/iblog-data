package cn.acyou.iblogdata.conf;

import cn.acyou.iblog.orika.OrikaMapper;
import cn.acyou.iblogdata.service.StudentService;
import cn.acyou.iblogdata.spring.MyFactoryBean;
import cn.acyou.iblogdata.spring.ScopeBean;
import cn.acyou.iblogdata.spring.UniqueBean;
import cn.acyou.iblogdata.utils.StudentConfig;
import cn.acyou.iblogdata.utils.StudentConfig2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;

import javax.servlet.MultipartConfigElement;

/**
 * @author youfang
 * @date 2018-05-17 下午 09:06
 */
@Configuration
public class BeanConfigurer {

    @Autowired
    private StudentService studentService;

    /**
     *一，单一Bean
     *
     * 装载
     * 1. 实例化;
     * 2. 设置属性值;
     * 3. 如果实现了BeanNameAware接口,调用setBeanName设置Bean的ID或者Name;
     * 4. 如果实现BeanFactoryAware接口,调用setBeanFactory 设置BeanFactory;
     * 5. 如果实现ApplicationContextAware,调用setApplicationContext设置ApplicationContext
     * 6. 调用BeanPostProcessor的预先初始化方法;
     * 7. 调用InitializingBean的afterPropertiesSet()方法;
     * 8. 调用定制init-method方法；
     * 9. 调用BeanPostProcessor的后初始化方法;
     *
     * spring容器关闭
     * 1. 调用DisposableBean的destroy();
     * 2. 调用定制的destroy-method方法;
     *
     *
     *
     * 二，多个Bean的先后顺序
     *
     * 优先加载BeanPostProcessor的实现Bean
     * 按Bean文件和Bean的定义顺序按bean的装载顺序（即使加载多个spring文件时存在id覆盖）
     * “设置属性值”（第2步）时，遇到ref，则在“实例化”（第1步）之后先加载ref的id对应的bean
     * AbstractFactoryBean的子类，在第6步之后,会调用createInstance方法，之后会调用getObjectType方法
     * BeanFactoryUtils类也会改变Bean的加载顺序
     *
     */

    @Bean(name = "studentConfigPrimary")
    @Primary
    @DependsOn(value = "studentConfig2")
    @Order(value = 12)
    public StudentConfig studentConfig(){
        return new StudentConfig(1, "看家的", 23);
    }

    @Bean(name = "studentConfig2")
    @Lazy(true)
    @Order(value = 1)
    public StudentConfig2 studentConfig2(){
        return new StudentConfig2(1, "看家的", 23);
    }

    @Bean
    public OrikaMapper orikaMapper(){
        return new OrikaMapper();
    }

    @Bean
    public MultipartConfigElement configElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("300MB");
        factory.setMaxRequestSize("300MB");
        return factory.createMultipartConfig();
    }


    @Bean(name = "fbStudentService")
    public MyFactoryBean myFactoryBean(){
        MyFactoryBean factoryBean = new MyFactoryBean();
        factoryBean.setInterfaceName("cn.acyou.iblogdata.service.StudentService");
        factoryBean.setTarget(studentService);
        return factoryBean;
    }



    @Bean(name = "siUniqueBean")
    public UniqueBean uniqueBean(){
        return new UniqueBean("司马懿");
    }

    /**
     * expected single matching bean but found 2
     *
     * 方法1 ：@Primary 提高优先级
     *
     * 方法2 ：使用
     *     `@Autowired
     *     `@Qualifier("zhuUniqueBean")
     * 方法3 ：使用
     *     `@Resource(name = "siUniqueBean")
     * @return UniqueBean
     */
    @Bean(name = "zhuUniqueBean")
    public UniqueBean uniqueBean2(){
        return new UniqueBean("诸葛亮");
    }


    /**
     * Spring中默认是单例
     * 通过Scope注解实现多例
     * 单例，注入的是唯一的对象。多例时是不同的对象
     * @return {@link ScopeBean}
     */
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ScopeBean scopeBean(){
        return new ScopeBean("出山...");
    }
}
