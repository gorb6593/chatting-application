package org.example.chatchatpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.chatchatpractice.entity.ChatMessage;
import org.example.chatchatpractice.entity.ChatRoom;
import org.example.chatchatpractice.service.ChatMessageService;
import org.example.chatchatpractice.service.ChatRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        ChatRoom chatRoom = chatRoomService.getChatRoomByName(roomName);
        List<ChatMessage> messages = chatMessageService.getMessagesByRoomName(roomName);
        model.addAttribute("roomName", roomName);
        model.addAttribute("messages", messages);
        return "chat";
    }

}
