package org.example.chatchatpractice.controller;

import org.example.chatchatpractice.entity.ChatMessage;
import org.example.chatchatpractice.entity.User;
import org.example.chatchatpractice.service.ChatMessageService;
import org.example.chatchatpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController {

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private UserService userService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessageService.saveMessage(chatMessage);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // 사용자 추가 로직
        String username = chatMessage.getSender();
        if (userService.userExists(username)) {
            // 이미 존재하는 사용자 처리
            chatMessage.setContent("User " + username + " already exists!");
            chatMessage.setType(ChatMessage.MessageType.ERROR);
        } else {
            User user = userService.addUser(username);
            headerAccessor.getSessionAttributes().put("username", username);
            chatMessage.setContent(username + " joined the chat!");
            chatMessage.setType(ChatMessage.MessageType.JOIN);
        }
        return chatMessage;
    }
}
