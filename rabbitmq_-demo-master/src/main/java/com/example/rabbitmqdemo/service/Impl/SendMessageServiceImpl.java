package com.example.rabbitmqdemo.service.Impl;

import com.example.rabbitmqdemo.config.RabbitMqReturnCallback;
import com.example.rabbitmqdemo.service.SendMessageService;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class SendMessageServiceImpl implements SendMessageService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void senMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageContext = "hello rabbitmq!!";
        String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("messageId", messageId);
        messageMap.put("messageContext", messageContext);
        messageMap.put("sendTime", sendTime);
        Gson gson = new Gson();
        String jsonMap = gson.toJson(messageMap);
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", jsonMap, new CorrelationData(messageId));
    }


    @Override
    public void senMessage2() {
        Gson gson = new Gson();
        String messageId = String.valueOf(UUID.randomUUID());
        String messageContext = "手动确认消息!!!";
        String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("messageId", messageId);
        messageMap.put("messageContext", messageContext);
        messageMap.put("sendTime", sendTime);
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting2", gson.toJson(messageMap));
    }

    @Override
    public void senMessage3() {
        Gson gson = new Gson();
        String messageId = String.valueOf(UUID.randomUUID());
        String messageContext = "手动确认消息!!!";
        String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("messageId", messageId);
        messageMap.put("messageContext", messageContext);
        messageMap.put("sendTime", sendTime);
        rabbitTemplate.convertAndSend("topicExchange", "MAX.aa.bb", gson.toJson(messageMap), new CorrelationData(messageId));
    }

    @Override
    public void senMessage4() {
        Gson gson = new Gson();
        String messageId = String.valueOf(UUID.randomUUID());
        String messageContext = "扇形交换器";
        String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("messageId", messageId);
        messageMap.put("messageContext", messageContext);
        messageMap.put("sendTime", sendTime);
        rabbitTemplate.convertAndSend("fanoutExchange", null, gson.toJson(messageMap), new CorrelationData(messageId));

    }
}
