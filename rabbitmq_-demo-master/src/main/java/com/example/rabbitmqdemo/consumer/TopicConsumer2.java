package com.example.rabbitmqdemo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "queue.B")
public class TopicConsumer2 {

    @RabbitHandler
    public void consumer1(String msg) {
        System.out.println("topic消费者B===" + msg);
    }


}
