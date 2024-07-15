package org.example.chatchatpractice.repository;

import org.example.chatchatpractice.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}