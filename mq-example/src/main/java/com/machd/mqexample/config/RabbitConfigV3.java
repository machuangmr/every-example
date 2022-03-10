package com.machd.mqexample.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * rabbit mq的配置v3，
 * 将所有的配置抽取出来，抽取在properties文件中
 *
 * @author machd
 * @version 3.0
 * @date 2022/3/9
 */
@Configuration
public class RabbitConfigV3 {
// 配置文件中的队列可以自动去创建

    @Value("${spring.rabbitmq.queuename}")
    private String queueName;

    @Value("${spring.rabbitmq.exchangename}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    @Resource(name = "myConnection")
    private ConnectionFactory connectionFactory;
    @Bean
    public Queue configQueue(){
        return new Queue(queueName, true);
    }

    @Bean
    public DirectExchange configExchange() {
        return new DirectExchange(exchangeName, true, false);
    }
    @Bean
    public Binding bingExchange() {
        return BindingBuilder.bind(configQueue()).to(configExchange()).with(routingKey);
    }

}
