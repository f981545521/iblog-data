package cn.acyou.iblogdata.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 * @author youfang
 * @version [1.0.0, 2018-08-04 下午 11:16]
 **/
@Component
@RabbitListener(queues = "hello")//该消息消费者监听hello这个消息队列
public class Receiver {

    @RabbitHandler//process方法是用来处理接收到的消息的，我们这里收到消息后直接打印即可。
    public void process(String msg) {
        System.out.println("Receiver:" + msg);
    }
}