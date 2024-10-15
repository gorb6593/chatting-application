package org.example.chatchatpractice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.net.URI;
import java.net.URLEncoder;

import static java.nio.charset.StandardCharsets.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final WebClient webClient;

    @Value("${data-go-kr.api.key}")
    private String serviceKey;

    @Value("${data-go-kr.api.endPoint}")
    private String apiBaseUrl;

    public Mono<String> getStockInfo(String itmsNm) {

        String path = "/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo";
        String fullUrl = apiBaseUrl + path + "?serviceKey=" + serviceKey + "&resultType=json&numOfRows=1000&beginBasDt=20240101&itmsNm=";
        String encode = URLEncoder.encode(itmsNm, UTF_8);
        fullUrl = fullUrl + encode;

        return webClient.get()
                .uri(URI.create(fullUrl))
                .accept(MediaType.APPLICATION_JSON)  // JSON 응답 요청
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> log.info("Response: {}", response))
                .doOnError(error -> log.error("Error occurred: ", error));
    }
}
