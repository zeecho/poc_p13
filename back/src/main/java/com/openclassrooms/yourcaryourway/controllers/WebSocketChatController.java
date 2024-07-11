//package com.openclassrooms.yourcaryourway.controllers;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
//
//@Controller
//public class WebSocketChatController {
//
//    @MessageMapping("/resume")
//    @SendTo("/start/initial")
//    public String chat(String msg) {
//        System.out.println(msg);
//        return msg;
//    }
//}

package com.openclassrooms.yourcaryourway.controllers;

import com.openclassrooms.yourcaryourway.model.Message;
import com.openclassrooms.yourcaryourway.model.User;
import com.openclassrooms.yourcaryourway.repository.MessageRepository;
import com.openclassrooms.yourcaryourway.repository.UserRepository;
import com.openclassrooms.yourcaryourway.request.MessageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class WebSocketChatController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/resume")
    @SendTo("/start/initial")
    public MessageRequest chat(MessageRequest msg) {
        System.out.println(msg);
        
        User user = userRepository.findByUsername(msg.getUsername());

        Message message = new Message();
        message.setUser(user);
        message.setContent(msg.getMessage());
        message.setSentAt(new Date());
        message.setIsRead(false);
        messageRepository.save(message);
        

        return msg;
    }
}
