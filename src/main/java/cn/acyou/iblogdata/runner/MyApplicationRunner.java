package cn.acyou.iblogdata.runner;

import cn.acyou.iblogdata.jmx.Hello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * 这里通过设定value的值来指定执行顺序
 * @author youfang
 * @version [1.0.0, 2018-8-28 下午 11:02]
 **/
@Slf4j
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments var1) throws Exception{
        log.info("MyApplicationRunner  was started!");
        registryMBean();
    }

    /**
     * 注册MBean
     */
    private void registryMBean(){
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer(); // 获取MBeanServer
        ObjectName name = null; // 构造一个名字
        try {
            name = new ObjectName("iblog:type=cacheClean");
            Hello hello = new Hello(); // 创建需要注册的对象
            mbs.registerMBean(hello, name); // 注册这个对象
            mbs.setAttribute(name, new Attribute("Name", "MBean的属性"));//属性首字母必须大写
            mbs.invoke(name, "print", null, null);//第三个参数为Object[],为传入的参数值，第四个参数为String[],指明参数类型
            log.info("MBean——————————>注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("MBean——————————>注册失败");
        }
    }

}
