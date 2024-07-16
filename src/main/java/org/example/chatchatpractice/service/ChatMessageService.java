package org.example.chatchatpractice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatchatpractice.entity.ChatMessage;
import org.example.chatchatpractice.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public ChatMessage saveMessage(ChatMessage chatMessage) {
        if (chatMessage.getChatRoom() == null) {
            throw new IllegalArgumentException("ChatRoom must be set before saving the message");
        }
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll();
    }
}
