package com.machd.mqexample.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(RabbitProducer.class);
    @Resource(name="myRabbitTemplate")
    private RabbitTemplate rabbitTemplate;


    @Resource(name="testRabbitTemplate")
    private RabbitTemplate testRabbitTemplate;

    @GetMapping("send/mymq")
    public String sendMsg(){
        logger.info("生产者发送了一条消息=====");
        rabbitTemplate.convertAndSend("myDirectExchange", "myDirect", "mymq");
        return "发送 mymq";
    }

    @GetMapping("send/testmq")
    public String testSendMsg(){
        testRabbitTemplate.convertAndSend("testDirectExchange", "direct", "testmq");
        return "发送testmymq";
    }



}
