package org.example.chatchatpractice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatchatpractice.entity.ChatMessage;
import org.example.chatchatpractice.entity.ChatRoom;
import org.example.chatchatpractice.service.ChatMessageService;
import org.example.chatchatpractice.service.ChatRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    @GetMapping("/chat")
    public String chatRoomList(Model model) {
        model.addAttribute("chatRooms", chatRoomService.getAllChatRooms());
        return "chatRoomList";
    }

    @PostMapping("/chat/create")
    public String createChatRoom(@RequestParam String roomName) {
        chatRoomService.createChatRoom(roomName);
        return "redirect:/chat";
    }

    @GetMapping("/chat/{roomName}")
    public String chatRoom(@PathVariable String roomName, Model model) {
        List<ChatMessage> messages = chatMessageService.getMessagesByRoomName(roomName);
        model.addAttribute("roomName", roomName);
        model.addAttribute("messages", messages);
        return "chat";
    }

    @GetMapping("/chat-bot")
    public String chatBot() {
        return "chatBot";
    }

    @PostMapping("/send-message")
    private ResponseEntity<String> sendMessage(@RequestBody String message) {
        try {
            return chatMessageService.sendRequest(message);
        } catch (IOException e) {
            log.error("message error :: {}", e.getMessage());
            return null;
        }
    }

}
