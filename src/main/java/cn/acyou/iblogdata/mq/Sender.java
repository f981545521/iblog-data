package cn.acyou.iblogdata.mq;

import cn.acyou.iblogdata.conf.RabbitMqConfig2;
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
    @Autowired(required = false)
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg = "hello rabbitmq:" + new Date();
        System.out.println("Sender:" + msg);
        this.rabbitTemplate.convertAndSend("hello", msg);
        rabbitTemplate.convertAndSend(RabbitMqConfig2.PER_QUEUE_TTL_EXCHANGE_NAME, RabbitMqConfig2.DELAY_QUEUE_PER_QUEUE_TTL_NAME, "aa");

    }
}