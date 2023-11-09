package com.yutsuki.telegram.service;

import com.yutsuki.telegram.model.request.MsgRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class TelegramService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${telegram.url}")
    private String telegramUrl;
    @Value("${telegram.chat-id}")
    private String chatId;
    @Value("${telegram.api-token}")
    private String apiToken;

    public void sendMessage(MsgRequest request) {
        String messageUrl = String.format(telegramUrl, apiToken);
        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set payload
        String jsonPayload = String.format("{\"chat_id\": \"%s\", \"text\": \"%s\"}", chatId, request.getText());
        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);
        this.restTemplate.postForEntity(messageUrl, entity, String.class);
        log.debug("URL: {}", messageUrl);
    }

}
