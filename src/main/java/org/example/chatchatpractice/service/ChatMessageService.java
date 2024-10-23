package org.example.chatchatpractice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.example.chatchatpractice.entity.ChatMessage;
import org.example.chatchatpractice.repository.ChatMessageRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatMessageService {

    @Value("${chat-api.naver.api-url}")
    private String API_URL;

    @Value("${chat-api.naver.api-key}")
    private String CLOVA_STUDIO_API_KEY;

    @Value("${chat-api.naver.gateway-key}")
    private String API_GATEWAY_KEY;

    @Value("${chat-api.naver.request-id}")
    private String REQUEST_ID;

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

    public List<ChatMessage> getMessagesByRoomName(String roomName) {
        return chatMessageRepository.findByRoomNameOrderByTimestampAsc(roomName);
    }

    public ResponseEntity<String> sendRequest(String userMessage) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            org.apache.hc.client5.http.classic.methods.HttpPost httpPost = new HttpPost(API_URL);

            // 헤더 설정
            httpPost.setHeader("X-NCP-CLOVASTUDIO-API-KEY", CLOVA_STUDIO_API_KEY);
            httpPost.setHeader("X-NCP-APIGW-API-KEY", API_GATEWAY_KEY);
            httpPost.setHeader("X-NCP-CLOVASTUDIO-REQUEST-ID", REQUEST_ID);
            httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");
            // Accept 헤더를 application/json으로 변경
            httpPost.setHeader("Accept", "application/json");

            // 요청 본문 생성
            JSONObject requestBody = new JSONObject();
            JSONArray messages = new JSONArray();
            JSONObject message = new JSONObject();
            message.put("role", "system");
            message.put("content", userMessage);
            messages.put(message);

            requestBody.put("messages", messages);
            requestBody.put("topP", 0.8);
            requestBody.put("topK", 0);
            requestBody.put("maxTokens", 2024);
            requestBody.put("temperature", 0.5);
            requestBody.put("repeatPenalty", 5.0);
            requestBody.put("stopBefore", new JSONArray());
            requestBody.put("includeAiFilters", true);

            org.apache.hc.core5.http.io.entity.StringEntity entity = new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8));
            httpPost.setEntity(entity);

            return httpClient.execute(httpPost, response -> {
                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                System.out.println("Response: " + responseBody);

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .header("Content-Type", "application/json")
                        .body(responseBody);
            });
        }
    }
}
