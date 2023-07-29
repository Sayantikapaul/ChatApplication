package com.sayantika.chatserver.controller;

import com.sayantika.chatserver.controller.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/message")    //app/message
    @SendTo("/chatroom/public")
    public Message recievePublicMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private",message);   //user/username/private
        return message;
    }
}
