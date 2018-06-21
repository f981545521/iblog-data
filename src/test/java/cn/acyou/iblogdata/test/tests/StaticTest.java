/*
 * 文 件 名:  StaticTest
 * 版    权:  Copyright 2018 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0.0>
 * 创 建 人:  youfang
 * 创建时间:   2018-06-21

 */
package cn.acyou.iblogdata.test.tests;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-21 上午 09:29]
 * @since [小倦鸟/远方模块]
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

    public static void main(String[] args) {
        System.out.println(StaticTest.getInfo());
        System.out.println(StaticTest.getInfo());
        System.out.println(StaticTest.getInfo());
    }

}
