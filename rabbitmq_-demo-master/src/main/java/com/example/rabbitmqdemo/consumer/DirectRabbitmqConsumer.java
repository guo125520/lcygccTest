package com.example.rabbitmqdemo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;


@Component
//@RabbitListener(queues = "TestDirectQueue")
public class DirectRabbitmqConsumer {

    @RabbitHandler
    public void process(String jsonMap) {

        System.out.println("消费者接收到消息======" + jsonMap);
    }
}
