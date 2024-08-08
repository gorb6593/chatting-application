package org.example.chatchatpractice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatchatpractice.response.response.StockPriceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.channels.MembershipKey;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final RestTemplate restTemplate;
    private final PolygonApiClient polygonApiClient;

//    @Value("${polygon.api.key}")
//    private String apiKey;
//
//    @Value("${polygon.api.base-url}")
//    private String baseUrl;

    public Object getStockData() {
        String url = "https://api.polygon.io/v2/aggs/ticker/AAPL/range/1/day/2023-01-09/2023-02-10?adjusted=true&sort=asc&apiKey=1zUVA_2C3CwGTWBazIunswBEXffYMuDw";
        return null;
    }

    public StockPriceResponse getStockPrice(String symbol) {
        StockPriceResponse response = polygonApiClient.getStockPrice(symbol);

        if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
            throw new RuntimeException("No stock data available for " + symbol);
        }

        // You can add additional business logic here if needed
        // For example, you might want to calculate some additional metrics or format the data

        return response;
    }


}
