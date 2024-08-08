package org.example.chatchatpractice.service;

import lombok.RequiredArgsConstructor;
import org.example.chatchatpractice.response.response.StockPriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class PolygonApiClient {

    private final RestTemplate restTemplate;
    @Value("${polygon.api.key}")
    private String apiKey;
    @Value("${polygon.api.baseUrl}")
    private String baseUrl;

    public StockPriceResponse getStockPrice(String symbol) {
        String url = baseUrl + "/v2/aggs/ticker/" + symbol + "/prev?apiKey=" + apiKey;
        return restTemplate.getForObject(url, StockPriceResponse.class);
    }
}
