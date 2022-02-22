package com.machd.mqexample.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
@Configuration
public class QueueConfig {
    @Bean
    public Queue myQueue(){
        return new Queue("myQueue");
    }


    @Bean
    public DirectExchange myExchange() {
        return new DirectExchange("myDirectExchange", true, false);
    }
    @Bean
    public Binding bindingMyExchange() {
        return BindingBuilder.bind(myQueue()).to(myExchange()).with("myDirect");
    }

    @Bean
    public Queue testQueue() {
        return new Queue("test");
    }

    @Bean
    public DirectExchange testExchange() {
        return new DirectExchange("testDirectExchange", true, false);
    }

    @Bean
    public Binding bindingExchange() {
        return BindingBuilder.bind(testQueue()).to(testExchange()).with("direct");
    }


}
