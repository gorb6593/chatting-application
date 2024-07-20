package org.example.chatchatpractice.controller;

import org.example.chatchatpractice.entity.ChatMessage;
import org.example.chatchatpractice.entity.ChatRoom;
import org.example.chatchatpractice.service.ChatMessageService;
import org.example.chatchatpractice.service.ChatRoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class WebSocketChatControllerTest {
    @Autowired
    private WebSocketChatController webSocketChatController;

    @MockBean
    private ChatMessageService chatMessageService;

    @MockBean
    private ChatRoomService chatRoomService;

    @MockBean
    private SimpMessagingTemplate messagingTemplate;

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
        chatMessage.setRoomName("Test Room");
    }

    @Test
    void sendMessage_ShouldSaveMessageAndSendToTopic() {
        when(chatRoomService.getChatRoomByName(anyString())).thenReturn(chatRoom);
        when(chatMessageService.saveMessage(any(ChatMessage.class))).thenReturn(chatMessage);

        webSocketChatController.sendMessage(chatMessage);

        verify(chatRoomService, times(1)).getChatRoomByName("Test Room");
        verify(chatMessageService, times(1)).saveMessage(chatMessage);
        verify(messagingTemplate, times(1)).convertAndSend(eq("/topic/chat/Test Room"), any(ChatMessage.class));
    }
}