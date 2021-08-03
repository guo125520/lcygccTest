package com.example.rabbitmqdemo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
//@RabbitListener(queues = "TestDirectQueue2")
public class DirectRabbitmqConsumer2 {


    @RabbitHandler
    public void process(Map messageMap) {
        System.out.println("消费者2接收到消息====" + messageMap);
    }

}
