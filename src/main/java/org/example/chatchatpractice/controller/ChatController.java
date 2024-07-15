package org.example.chatchatpractice.controller;

import lombok.NoArgsConstructor;
import org.example.chatchatpractice.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    private final ChatMessageService chatMessageService;

    public ChatController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        model.addAttribute("messages", chatMessageService.getAllMessages());
        return "chat";
    }

}
