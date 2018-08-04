package cn.acyou.iblogdata.test.tests;

import cn.acyou.iblogdata.mq.Sender;
import cn.acyou.iblogdata.test.base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-04 下午 11:20]
 **/
public class RabbitMqTest extends BaseTest {
    @Autowired
    private Sender sender;

    @Test
    public void contextLoads() {
        sender.send();
    }
}
