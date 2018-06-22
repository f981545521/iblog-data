package cn.acyou.iblogdata.test.classLoader;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-21 上午 09:29]
 **/
public class StaticTest {

    private static String client = getClient();

    static {
        System.out.println("静态代码块");
    }

    private static String getClient(){
        System.out.println("获取客户端");
        return "SS";
    }

    public static String getInfo(){
        System.out.println("获取Info信息");
        return client;
    }



}
