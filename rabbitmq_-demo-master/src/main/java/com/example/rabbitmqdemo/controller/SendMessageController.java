package com.example.rabbitmqdemo.controller;

import com.example.rabbitmqdemo.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sendMessage")
public class SendMessageController {

    @Autowired
    SendMessageService sendMessageService;

    @GetMapping("/directSend1")
    public void directSend1() {
        sendMessageService.senMessage();
    }

    @GetMapping("/directSend2")
    public void directSend2() {
        sendMessageService.senMessage2();
    }

    @GetMapping("/topicSend1")
    public void topicSend1() {
        sendMessageService.senMessage3();
    }

    @GetMapping("/fanoutExchange1")
    public void fanoutExchange() {
        sendMessageService.senMessage4();
    }

}
