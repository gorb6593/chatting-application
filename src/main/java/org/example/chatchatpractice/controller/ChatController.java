package org.example.chatchatpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.chatchatpractice.service.ChatMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatMessageService chatMessageService;

    @GetMapping("/chat")
    public String chat(Model model) {
        model.addAttribute("messages", chatMessageService.getAllMessages());
        return "chat";
    }

    @GetMapping("/shooting")
    public String shooting() {
        return "shooting";
    }

}
