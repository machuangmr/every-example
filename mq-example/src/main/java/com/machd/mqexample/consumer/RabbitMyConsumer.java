package com.machd.mqexample.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @RabbitHandler
    public void handler(String msg, Message message, Channel channel) {
        // 方法名称无所谓，主要是要是用@RabbitHandler注解
        System.out.println("dev环境队列收到消息===" + msg);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
