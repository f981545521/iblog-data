package cn.acyou.iblogdata.test.classLoader;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-22 上午 09:53]
 **/
public class StaticTest2 {
    public static void main(String[] args) throws Exception {
        Class.forName("cn.acyou.iblogdata.test.classLoader.StaticTest");
        //initialize为false不初始化类中的静态变量/静态代码块
        //Class.forName("cn.acyou.iblogdata.test.tests.StaticTest", false, StaticTest2.class.getClassLoader());
        System.out.println("123");
/*        System.out.println(StaticTest.getInfo());
        Thread.sleep(1000);
        System.out.println(StaticTest.getInfo());
        System.out.println(StaticTest.getInfo());*/
    }
}
