package com.example.rabbitmqdemo.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


@Component
public class RabbitMqTemplateConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println("消息推送到交换机成功");
            System.out.println("消息唯一标识：" + correlationData);
        } else {
            System.out.println("消息推送到交换机失败");
            System.out.println("消息唯一标识：" + correlationData);
            System.out.println("失败原因：" + s);
        }

    }
}
