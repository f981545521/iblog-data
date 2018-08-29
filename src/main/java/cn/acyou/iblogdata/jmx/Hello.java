package cn.acyou.iblogdata.jmx;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @author youfang
 * @version [1.0.0, 2018-8-28 下午 10:50]
 **/
public class Hello implements HelloMBean {
    public String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String print() {
        return "Hello," + name + "!!";
    }

    public static void main(String args[]) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer(); // 获取MBeanServer
        ObjectName name = new ObjectName("iblog:type=cacheClean"); // 构造一个名字
        Hello hello = new Hello(); // 创建需要注册的对象
        mbs.registerMBean(hello, name); // 注册这个对象
        mbs.setAttribute(name, new Attribute("Name", "bianjie"));//属性首字母必须大写
        mbs.invoke(name, "print", null, null);//第三个参数为Object[],为传入的参数值，第四个参数为String[],指明参数类型
        Thread.sleep(Long.MAX_VALUE);
    }

}