package com.imooc.order.controller;


import com.imooc.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("sendMessage")
    public void process(){
        String message = "now"+new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }
}
