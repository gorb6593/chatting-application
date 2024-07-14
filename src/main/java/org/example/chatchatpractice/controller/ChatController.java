package org.example.chatchatpractice.controller;

import lombok.NoArgsConstructor;
import org.example.chatchatpractice.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageService chatMessageService;

    @GetMapping("/")
    public String getChat(Model model) {
        model.addAttribute("messages", chatMessageService.getAllMessages());
        return "chat";
    }

}
