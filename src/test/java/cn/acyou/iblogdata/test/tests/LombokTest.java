package cn.acyou.iblogdata.test.tests;

import cn.acyou.iblogdata.entity.OrderInfo;

/**
 * @author acyou
 * @version [1.0.0, 2020-3-22 下午 04:41]
 **/
public class LombokTest {

    public static void main(String[] args) {
        OrderInfo orderInfo = new OrderInfo();
        System.out.println(orderInfo);
        OrderInfo build = OrderInfo.builder().orderId(10L).build();
        System.out.println(build);
    }
}
