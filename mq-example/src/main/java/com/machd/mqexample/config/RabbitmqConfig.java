package com.machd.mqexample.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 请添加注释
 *
 * @author macd
 * @version 3
 * @since 3.0
 */
@Configuration
public class RabbitmqConfig {


    @Bean(name ="myConnection")
    @Primary
    public ConnectionFactory myConnection(@Value("${spring.rabbitmq.dev.host}") String host, @Value("${spring.rabbitmq.dev.port}") int port,
                                          @Value("${spring.rabbitmq.dev.username}") String username,
                                          @Value("${spring.rabbitmq.dev.password}") String password,
                                          @Value("${spring.rabbitmq.dev.vhost}") String vhost){

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(vhost);
        return connectionFactory;
    }



    @Bean(name ="testConnection")
    public ConnectionFactory testConnection(@Value("${spring.rabbitmq.test.host}") String host, @Value("${spring.rabbitmq.test.port}") int port,
                                            @Value("${spring.rabbitmq.test.username}") String username,
                                            @Value("${spring.rabbitmq.test.password}") String password,
                                            @Value("${spring.rabbitmq.test.vhost}") String vhost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(vhost);
        return connectionFactory;
    }

    @Bean("myRabbitTemplate")
    public RabbitTemplate myRabbitTemplate(@Qualifier("myConnection") ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean("testRabbitTemplate")
    public RabbitTemplate testRabbitTemplate(@Qualifier("testConnection") ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean(name = "testContainerFactory")
    public SimpleRabbitListenerContainerFactory testSimpleRabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer rabbitListenerContainerFactoryConfigurer,
            @Qualifier("testConnection") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory containerFactory=new SimpleRabbitListenerContainerFactory();
        containerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitListenerContainerFactoryConfigurer.configure(containerFactory,connectionFactory);
        return containerFactory;
    }

    /**
     * 增加dev 环境ContainerFactory
     */
    @Bean(name = "devContainerFactory")
    public SimpleRabbitListenerContainerFactory devSimpleRabbitListenerContainerFactory(
            @Qualifier("myConnection") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setAutoStartup(true);
        return factory;
    }


}
