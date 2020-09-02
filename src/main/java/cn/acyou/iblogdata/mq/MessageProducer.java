package cn.acyou.iblogdata.mq;


import cn.acyou.iblogdata.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 如果消息没有到exchange,则confirm回调,ack=false
 *
 * 如果消息到达exchange,则confirm回调,ack=true
 *
 * exchange到queue成功,则不回调return
 *
 * exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
 *
 * 用户消息生产者
 * @author youfang
 * @version [1.0.0, 2020/9/1]
 **/
@Slf4j
@Component
public class MessageProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送延迟消息
     *
     * @param time    时间 单位s
     * @param content 内容
     */
    public void sendDelayMessage(Integer time, String content) {
        log.info("发送延迟消息，延迟{} s，内容：{}。", time, content);
        int timeMs = time * 1000;
        rabbitTemplate.setConfirmCallback(confirmCallback);
        //支持ReturnCallback
        //rabbitTemplate.setMandatory(true);
        //rabbitTemplate.setReturnCallback(returnCallback);
        //rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.convertAndSend(MQConstant.DELAY_EXCHANG, MQConstant.DELAY_ROUTING_KEY, content, message -> {
                    //注意这里时间可以使long，而且是设置header
                    message.getMessageProperties().setHeader("x-delay", timeMs);
                    return message;
                }, genCorrelationData()
        );
        log.info("发送延迟消息成功，{}s后执行。", time);
    }

    /**
     * 生成自定义反馈消息
     * @return 自定义反馈消息
     */
    private CorrelationData genCorrelationData(){
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(RandomUtil.randomUuid());
        return correlationData;
    }

    /**
     * 回调函数: return确认
     * 当消息未投递到queue时的反馈。
     * 设置消息返回回调方法；该方法执行时则表示消息投递失败
     */
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            //表示消息发送到交换机，但是没有找到队列，这里记录日志
            log.warn("ReturnCallback -> 消息{}，发送到队列失败，应答码：{}，原因：{}，交换器: {}，路由键：{}",
                    message,
                    replyCode,
                    replyText,
                    exchange,
                    routingKey);
        }
    };

    /**
     * 回调函数: confirm确认
     * 注意：Confirm模式只管有无投递到exchange，而不管有无发送到队列当中。
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {

        /**
         * 消息确认回调方法
         *
         * @param correlationData 相关数据
         * @param ack             为true时，表示投递成功；为false表示投递失败；
         * @param cause           为投递失败的原因
         */
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            if(ack){
                log.info("MessageProducer ConfirmCallback 发送成功！");
            } else {
                //失败则进行具体的后续操作:重试 或者补偿等手段
                log.error("MessageProducer ConfirmCallback 发送失败！");
                log.error("correlationData :" + correlationData);
                log.error("cause :" + cause);
                log.error("correlationData :" + correlationData);
            }
        }
    };
}
