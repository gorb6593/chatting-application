package org.example.chatchatpractice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

//    @Value("${data-go-kr.api.endPoint}")
//    private String apiBaseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
//                .baseUrl(apiBaseUrl)
                .codecs(configurer -> configurer.defaultCodecs().enableLoggingRequestDetails(true))
                .build();
    }

}
