package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author youfang
 * @version [1.0.0, 2018-12-11 下午 05:05]
 **/
@Slf4j
@Controller
@RequestMapping("mq")
public class MqController {

    @Autowired(required = false)
    private AmqpTemplate amqpTemplate;

    @RequestMapping(value = "sendMessage", method = {RequestMethod.GET})
    @ResponseBody
    public ResultInfo addStudent(String message){
        amqpTemplate.convertAndSend("hello", message);
        return new ResultInfo("发送成功");
    }
}
