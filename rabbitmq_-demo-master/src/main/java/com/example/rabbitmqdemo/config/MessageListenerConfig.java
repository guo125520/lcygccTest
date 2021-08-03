package com.example.rabbitmqdemo.config;

import com.example.rabbitmqdemo.consumer.MyAckReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class MessageListenerConfig {

    @Autowired
    CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    MyAckReceiver myAckReceiver;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueueNames("TestDirectQueue", "TestDirectQueue2");
        container.setMessageListener(myAckReceiver);
        return container;
    }
}
