package cn.acyou.iblogdata.mq;

import cn.acyou.iblogdata.constant.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 * @author youfang
 * @version [1.0.0, 2018-08-04 下午 11:16]
 **/
@Slf4j
@Component
@RabbitListener(queues = AppConstant.MQ_CHANNEL_MAIN, containerFactory="rabbitListenerContainerFactory")//该消息消费者监听hello这个消息队列
public class Receiver {

    @RabbitHandler//process方法是用来处理接收到的消息的，我们这里收到消息后直接打印即可。
    public void process(String message) {
        System.out.println("RabbitMQ ————————————> Receiver : " + message);
    }
}