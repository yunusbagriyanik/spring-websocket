package com.yunusbagriyanik.springwebsocket.controller;

import com.yunusbagriyanik.springwebsocket.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/address")
    public void onMessage(@Payload Message message) {
        System.out.println(message.toString());
        this.messagingTemplate.convertAndSend("/channelHook", message);
    }

    @PostMapping("/push")
    public void pushMessage(@RequestBody Message message) {
        System.out.println(message);
        messagingTemplate.convertAndSend("/channelHook", message);
    }
}
