package cn.acyou.iblogdata.mq;


import cn.acyou.iblogdata.utils.DateUtil;
import cn.acyou.iblogdata.utils.RandomUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 用户消息消费者
 * @author youfang
 * @version [1.0.0, 2020/9/1]
 **/
@Slf4j
@Component
@RabbitListener(queues = MQConstant.DELAY_QUEUE)
public class MessageConsumer {
    /**
     * 定时消息队列消费监听回调
     *
     * @param content 消息内容
     * @param message 消息
     * @param channel 信道
     */
    @RabbitHandler
    public void consultReceiveDelay(String content, Message message, Channel channel) {
        log.info("----------------接收延迟队列消息--------------------");
        log.info("接收时间:{},接受内容:{}", DateUtil.getCurrentDateFormat(), content);
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            if (RandomUtil.random01() == 0){
                //确认消息
                log.info("确认消息！");
                channel.basicAck(deliveryTag, false);
            }else {
                //恢复到队列，由其他消费者处理。如果每个消费者都处理不了？会死循环
                log.info("恢复到队列！");
                channel.basicRecover(true);
            }
            //确认消息
            //channel.basicAck(deliveryTag, false);
            //拒绝消息：消息会被丢弃，不会重回队列
            //channel.basicReject(deliveryTag,false);
            //否认消息：（注意）重新入队列然后一直重新消费造成死循环
            //channel.basicNack(deliveryTag, false, true);
            //恢复消息到队列，参数是是否requeue，true则重新入队列，并且尽可能的将之前recover的消息投递给其他消费者消费，而不是自己再次消费。false则消息会重新被投递给自己。
            //channel.basicRecover(true);
        }catch (Exception e) {
            log.error("============消费失败,尝试消息补发再次消费!==============");
            throw new RuntimeException(e.getMessage());
            //try {
            //    //丢弃这条消息
            //    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            //} catch (IOException ioException) {
            //    ioException.printStackTrace();
            //}
        }
    }
}
