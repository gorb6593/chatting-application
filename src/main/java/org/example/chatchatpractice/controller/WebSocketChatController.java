package org.example.chatchatpractice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.example.chatchatpractice.entity.ChatMessage;
import org.example.chatchatpractice.entity.ChatRoom;
import org.example.chatchatpractice.entity.User;
import org.example.chatchatpractice.service.ChatMessageService;
import org.example.chatchatpractice.service.ChatRoomService;
import org.example.chatchatpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WebSocketChatController {

    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public void sendMessage(ChatMessage chatMessage) {
//        log.info("chatMessage : {}", chatMessage);
//        chatMessageService.saveMessage(chatMessage);
//    }
//
//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatMessage addUser(ChatMessage chatMessage) {
//        chatMessage.setContent(chatMessage.getSender() + " joined the chat");
//        chatMessage.setType(ChatMessage.MessageType.JOIN);
//        return chatMessageService.saveMessage(chatMessage);
//    }
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        ChatRoom chatRoom = chatRoomService.getChatRoomByName(chatMessage.getRoomName());
        chatMessage.setChatRoom(chatRoom);
        //ChatMessage savedMessage = chatMessageService.saveMessage(chatMessage);
        return chatMessageService.saveMessage(chatMessage);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        //headerAccessor.getSessionAttributes().put("roomName", chatMessage.getRoomName());
        //messagingTemplate.convertAndSend("/topic/chat/" + chatMessage.getRoomName(), chatMessage);
    }



}
