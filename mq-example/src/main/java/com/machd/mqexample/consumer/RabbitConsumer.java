package com.machd.mqexample.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

import javax.crypto.MacSpi;
import java.io.IOException;


/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
@Component
@RabbitListener(queues = "test", containerFactory = "testContainerFactory")
public class RabbitConsumer {
    @RabbitHandler
    public void process(Channel channel, Message message, String msg) {

        try {
            System.out.println("test Queue received msg : " + msg );
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

}
