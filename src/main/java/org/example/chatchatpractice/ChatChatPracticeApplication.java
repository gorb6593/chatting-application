package org.example.chatchatpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class ChatChatPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatChatPracticeApplication.class, args);
    }

}
