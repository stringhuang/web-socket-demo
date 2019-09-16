package com.zg.websocket2.controller;

import com.zg.websocket2.entity.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/send")
    public Greeting greetingTo(@RequestParam("name") String name) {
        Greeting greeting = new Greeting("broadcast msg " + name);
        this.simpMessagingTemplate.convertAndSend("/topic/greetings", greeting);
        return greeting;
    }

    @GetMapping("/sendto")
    public Greeting greetingTo(@RequestParam("name") String name, @RequestParam("id") String id) {
        Greeting greeting = new Greeting("p2p msg " + name);
        // 以下两个方法，效果等同
        this.simpMessagingTemplate.convertAndSend("/user/" + id + "/msg", greeting);
//        this.simpMessagingTemplate.convertAndSendToUser(id, "/msg", greeting);
        return greeting;
    }
}
