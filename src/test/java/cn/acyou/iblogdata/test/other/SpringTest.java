package cn.acyou.iblogdata.test.other;

import cn.acyou.iblogdata.service.StudentService;
import cn.acyou.iblogdata.test.base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author youfang
 * @version [1.0.0, 2018-12-13 下午 01:42]
 * @since [天天健身/促销模块]
 **/
public class SpringTest extends BaseTest {

    @Autowired
    private ApplicationContext context;


    /**
     * 测试验证FactoryBean原理，代理一个servcie在调用其方法的前后，打印日志亦可作其他处理
     * 从ApplicationContext中获取自定义的FactoryBean
     * context.getBean(String beanName) ---> 最终获取到的Object是FactoryBean.getObejct(),
     * 使用Proxy.newInstance生成service的代理类
     */
    @Test
    public void testFactoryBean() {
        StudentService studentService = (StudentService) context.getBean("fbStudentService");
        String res = studentService.sayThanks();
        System.out.println(res);
    }
}
