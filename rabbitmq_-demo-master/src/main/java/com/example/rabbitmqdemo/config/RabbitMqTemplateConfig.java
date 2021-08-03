package com.example.rabbitmqdemo.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqTemplateConfig implements CommandLineRunner {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws Exception {
        initializationRabbitMqTemplateCallBack();
    }

    /**
     * 设置rabbitTemplate 全局的错误回调
     */
    private void initializationRabbitMqTemplateCallBack() {
        // 只要消息没到交换机,只会回调ConfirmCallback,到了交换机,ConfirmCallback和ReturnsCallback 都会回调
        // 消息消费成功的话,会回调ConfirmCallback
        // 回调ReturnsCallback的情况只有一种，那就是消息到了交换机，但是没找到队列，所以会回调ReturnsCallback
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnsCallback(new RabbitMqReturnCallback());

        rabbitTemplate.setConfirmCallback(new RabbitMqTemplateConfirmCallback());
    }
}
