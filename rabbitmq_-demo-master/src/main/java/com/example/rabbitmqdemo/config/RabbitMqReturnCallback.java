package com.example.rabbitmqdemo.config;


import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


@Component
public class RabbitMqReturnCallback implements RabbitTemplate.ReturnsCallback {
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        Map<String, String> hashMap = new HashMap<>();
        System.out.println(Charset.defaultCharset().name());
        String json = new String(returnedMessage.getMessage().getBody());
        System.out.println(hashMap.toString());
        System.out.println("消息key为===" + returnedMessage.getRoutingKey());
        System.out.println("回复错误码为===" + returnedMessage.getReplyCode());
        System.out.println("回复内容为===" + returnedMessage.getReplyText());
        System.out.println("交换器为===" + returnedMessage.getExchange());
    }
}
