package com.machd.mqexample.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
@RestController
public class RabbitProducer {

    @Resource(name="myRabbitTemplate")
    private RabbitTemplate rabbitTemplate;


    @Resource(name="testRabbitTemplate")
    private RabbitTemplate testRabbitTemplate;

    @GetMapping("send/mymq")
    public String sendMsg(){
        rabbitTemplate.convertAndSend("myDirectExchange", "myDirect", "mymq");
        return "发送 mymq";
    }

    @GetMapping("send/testmq")
    public String testSendMsg(){
        testRabbitTemplate.convertAndSend("testDirectExchange", "direct", "testmq");
        return "发送testmymq";
    }



}
