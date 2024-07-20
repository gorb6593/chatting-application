package org.example.chatchatpractice.service;

import org.example.chatchatpractice.entity.ChatMessage;
import org.example.chatchatpractice.entity.ChatRoom;
import org.example.chatchatpractice.repository.ChatMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ChatMessageServiceTest {
    @Mock
    private ChatMessageRepository chatMessageRepository;

    @InjectMocks
    private ChatMessageService chatMessageService;

    private ChatMessage chatMessage;
    private ChatRoom chatRoom;

    @BeforeEach
    void setUp() {
        chatRoom = new ChatRoom();
        chatRoom.setName("Test Room");

        chatMessage = new ChatMessage();
        chatMessage.setId(1L);
        chatMessage.setSender("Test User");
        chatMessage.setContent("Test Message");
        chatMessage.setChatRoom(chatRoom);
    }

    @Test
    void saveMessage_ShouldSaveAndReturnMessage() {
        when(chatMessageRepository.save(any(ChatMessage.class))).thenReturn(chatMessage);

        ChatMessage savedMessage = chatMessageService.saveMessage(chatMessage);

        assertNotNull(savedMessage);
        assertEquals(chatMessage.getId(), savedMessage.getId());
        assertEquals(chatMessage.getSender(), savedMessage.getSender());
        assertEquals(chatMessage.getContent(), savedMessage.getContent());
        assertEquals(chatMessage.getChatRoom(), savedMessage.getChatRoom());

        verify(chatMessageRepository, times(1)).save(chatMessage);
    }

    @Test
    void saveMessage_WithNullChatRoom_ShouldThrowException() {
        chatMessage.setChatRoom(null);

        assertThrows(IllegalArgumentException.class, () -> {
            chatMessageService.saveMessage(chatMessage);
        });
    }
}