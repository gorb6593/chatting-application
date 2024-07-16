package org.example.chatchatpractice.repository;

import org.example.chatchatpractice.entity.ChatMessage;
import org.example.chatchatpractice.entity.ChatRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ChatMessageRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Test
    void saveChatMessage_ShouldSaveAndRetrieveMessage() {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName("Test Room");
        entityManager.persist(chatRoom);

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender("Test User");
        chatMessage.setContent("Test Message");
        chatMessage.setChatRoom(chatRoom);

        chatMessageRepository.save(chatMessage);

        ChatMessage foundMessage = chatMessageRepository.findById(chatMessage.getId()).orElse(null);
        assertNotNull(foundMessage);
        assertEquals("Test User", foundMessage.getSender());
        assertEquals("Test Message", foundMessage.getContent());
        assertEquals("Test Room", foundMessage.getChatRoom().getName());
    }
}