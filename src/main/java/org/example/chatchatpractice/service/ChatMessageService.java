package org.example.chatchatpractice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.chatchatpractice.entity.ChatMessage;
import org.example.chatchatpractice.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Transactional
    public ChatMessage saveMessage(ChatMessage message) {
        try {
            return chatMessageRepository.save(message);
        } catch (Exception e) {
            log.error("Error saving chat message: ", e);
            throw e;
        }
    }

    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll();
    }
}
