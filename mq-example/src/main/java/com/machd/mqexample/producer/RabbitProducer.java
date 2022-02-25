package com.machd.mqexample.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.machd.mqexample.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.crypto.KeySelector;
import java.nio.charset.StandardCharsets;

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


//    @Resource(name="testRabbitTemplate")
//    private RabbitTemplate testRabbitTemplate;

    @GetMapping("send/mymq")
    public String sendMsg(){
        logger.info("生产者发送了一条消息=====");
        rabbitTemplate.convertAndSend("myDirectExchange", "myDirect", "mymq");
        return "发送 mymq";
    }

    @GetMapping("send/testmq")
    public String testSendMsg(){
        //testRabbitTemplate.convertAndSend("testDirectExchange", "direct", "testmq");
        return "发送testmymq";
    }

    @GetMapping("/send/order")
    public String sendOrder(){
        Order order = new Order();
        order.setOrderName("测试订单");
        order.setUserId("123");
        order.setId(1L);
        logger.info("生产者订单已经发送～～～");
        MessageProperties messageProperties = new MessageProperties();
        Message message = new Jackson2JsonMessageConverter().toMessage(order, messageProperties);
        rabbitTemplate.convertAndSend("myDirectExchange", "myDirect", message);
        return "订单发送成功";
    }

    @GetMapping("send/with/json")
    public String sendWithJson() throws JsonProcessingException {
        Order order = new Order();
        order.setOrderName("测试订单2");
        order.setUserId("321");
        order.setId(2L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(order);
        rabbitTemplate.convertAndSend("myDirectExchange", "myDirect", json);
        logger.info("生产者发送消息={}", json);
        return "send order with json success";
    }

}
