package com.example.rabbitmqdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FanoutRabbitMqConfig {

    // 创建扇形交换机
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    // 创建队列绑定
    @Bean
    Queue fanoutQueueA() {
        return new Queue("fanoutQueueA");
    }

    @Bean
    Queue fanoutQueueB() {
        return new Queue("fanoutQueueB");
    }

    // 队列绑定交换机
    @Bean
    Binding queueABindingExchange() {
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }

    @Bean
    Binding queueBBindingExchange() {
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }

}
