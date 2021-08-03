package com.example.rabbitmqdemo.consumer;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


@Component
public class MyAckReceiver implements ChannelAwareMessageListener {
    int i = 0;

    @Override
    public void onMessage(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        Gson gson = new Gson();
        try {
            if (message.getMessageProperties().getConsumerQueue().equals("TestDirectQueue")) {

                Map<String, String> map = gson.fromJson(new String(message.getBody()), Map.class);
                System.out.println("消息id{messageId}" + map.get("messageId"));
                System.out.println("消息实体{messageContext}" + map.get("messageContext"));
                System.out.println("发送时间{sendTime}" + map.get("sendTime"));
                System.out.println("消息标签{deliveryTag}" + deliveryTag);
                if (i < 1) {
                    i++;
                    int i = 1 / 0;
                }
                channel.basicAck(deliveryTag, true);// 确认消息已送到

            } else if (message.getMessageProperties().getConsumerQueue().equals("TestDirectQueue2")) {
                Map<String, String> map = gson.fromJson(new String(message.getBody()), Map.class);
                System.out.println("消息id{messageId}" + map.get("messageId"));
                System.out.println("消息实体{messageContext}" + map.get("messageContext"));
                System.out.println("发送时间{sendTime}" + map.get("sendTime"));
                System.out.println("消息标签{deliveryTag}" + deliveryTag);
                channel.basicAck(deliveryTag, true);// 确认消息已送到
            }
        } catch (Exception e) {
            System.out.println("发生异常,消息重新进入消息队列");
            System.out.println(e);
            channel.basicReject(deliveryTag, true);
        }
    }
}
