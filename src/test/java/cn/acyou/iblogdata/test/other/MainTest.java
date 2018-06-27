package cn.acyou.iblogdata.test.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-27 上午 11:21]
 **/
public class MainTest implements Cloneable{
    private static Logger logger = null;

    public MainTest() {
        logger = LoggerFactory.getLogger(getClass().getName());
        logger.info("调用构造函数");
    }


    public static void main(String[] args) throws Exception{
        //new MainTest();//1. 通过new 关键字
        //Class clazz = Class.forName("cn.acyou.iblogdata.test.other.MainTest");
        //clazz.newInstance();// 2.1 通过反射newInstance()
        //MainTest.class.newInstance();// 2.2 通过反射newInstance()

        //MainTest test = new MainTest();
        //Object obj = test.clone();// 3. 通过clone方法获取对象
        //System.out.println(obj);

        //Constructor<MainTest> constructor = MainTest.class.getConstructor();//2. 通过Constructor 获取构造器来实例化
        //constructor.newInstance();

        System.out.println("开始");
        logger.debug("哈哈哈哈！");
    }


}
