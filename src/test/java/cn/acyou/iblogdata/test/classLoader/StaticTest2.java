package cn.acyou.iblogdata.test.classLoader;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-22 上午 09:53]
 **/
public class StaticTest2 {
    /**
     * 当一个类被主动使用时，Java虚拟就会对其初始化，如下六种情况为主动使用：
     *
     * 1. 当创建某个类的新实例时（如通过new或者反射，克隆，反序列化等）
     * 2. 当调用某个类的静态方法时
     * 3. 当使用某个类或接口的静态字段时
     * 4. 当调用Java API中的某些反射方法时，比如类Class中的方法，或者java.lang.reflect中的类的方法时
     * 5. 当初始化某个子类时
     * 6. 当虚拟机启动某个被标明为启动类的类（即包含main方法的那个类）
     */
    public static void main(String[] args) throws Exception {
        //Class.forName("cn.acyou.iblogdata.test.classLoader.StaticTest");
        //initialize为false不初始化类中的静态变量/静态代码块
        //Class.forName("cn.acyou.iblogdata.test.tests.StaticTest", false, StaticTest2.class.getClassLoader());

        //System.out.println(StaticTest.getInfo());
        //StaticTest staticTest = new StaticTest();
        System.out.println("123");
/*        System.out.println(StaticTest.getInfo());
        Thread.sleep(1000);
        System.out.println(StaticTest.getInfo());
        System.out.println(StaticTest.getInfo());*/
    }
}
