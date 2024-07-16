package org.example.chatchatpractice.service;

import lombok.RequiredArgsConstructor;
import org.example.chatchatpractice.entity.ChatRoom;
import org.example.chatchatpractice.entity.User;
import org.example.chatchatpractice.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom getChatRoomByName(String name) {
        return chatRoomRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));
    }

    public void addUserToChatRoom(User user, ChatRoom chatRoom) {
        chatRoom.getUsers().add(user);
        chatRoomRepository.save(chatRoom);
    }
}
