package com.machd.mqexample.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.machd.mqexample.entity.Order;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @Auth:machd
 * @Date:2022/2/23
 * @version:3.0
 */
@Component
@RabbitListener(queues = "myQueue", containerFactory = "devContainerFactory")
public class RabbitMyConsumer {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMyConsumer.class);

    @RabbitHandler
    public void handler(@Payload Order order, Message message, Channel channel) throws IOException {
        // 方法名称无所谓，主要是要是用@RabbitHandler注解
       // System.out.println("dev环境队列收到消息===" + msg);
        try {
            //Order order = (Order)new Jackson2JsonMessageConverter().fromMessage(message);
            //String s = message.toString();
            //int i = 1 / 0;
            //ObjectMapper objectMapper = new ObjectMapper();
           // Order order1 = objectMapper.readValue(order, Order.class);
            logger.info("消费者收到的订单消息={}", order);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            logger.info("消费者有报错，消息重新入队列, 消息={}", order);
            // channel.basickNack和basicReject的区别是，前者可以拒绝多次，后者只能拒绝一次
            // channel.basicNack();
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            e.printStackTrace();
        }
    }

}
