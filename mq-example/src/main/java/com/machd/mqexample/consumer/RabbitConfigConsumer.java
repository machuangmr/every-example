package com.machd.mqexample.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 将所有的配置抽取出来
 *
 * @author machd
 * @version 3.0
 * @date 2022/3/9
 */
@Component
@RabbitListener(queues = "${spring.rabbitmq.queuename}", containerFactory = "devContainerFactory")
public class RabbitConfigConsumer {
    private static final Logger logger = LoggerFactory.getLogger(RabbitConfigConsumer.class);

    @RabbitHandler
    public void process(String msg, Message message, Channel channel) throws IOException {
        try {
            logger.info("配置 mq消费者收到消息={}", msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }catch (Exception e) {
            e.printStackTrace();
            // 如果有消费失败的日志，那么我们需要将消费失败的消息保存并记录下来
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
