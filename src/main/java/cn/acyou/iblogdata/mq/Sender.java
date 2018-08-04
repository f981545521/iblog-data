package cn.acyou.iblogdata.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 消息生产者
 * 发送消息我们有一个现成的封装好的对象AmqpTemplate，通过AmqpTemplate我们可以直接向某一个消息队列发送消息
 * @author youfang
 * @version [1.0.0, 2018-08-04 下午 11:15]
 **/
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg = "hello rabbitmq:" + new Date();
        System.out.println("Sender:" + msg);
        this.rabbitTemplate.convertAndSend("hello", msg);
    }
}