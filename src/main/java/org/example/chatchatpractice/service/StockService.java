package org.example.chatchatpractice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatchatpractice.response.PolygonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    private final RestTemplate restTemplate;

    @Value("${polygon.api.key}")
    private String apiKey;

    @Value("${polygon.api.base-url}")
    private String baseUrl;

    public Object getStockData(String ticker) {
        //String url = "https://api.polygon.io/v2/aggs/ticker/AAPL/range/1/day/2023-01-09/2023-02-10?adjusted=true&sort=asc&apiKey=1zUVA_2C3CwGTWBazIunswBEXffYMuDw";
        return null;
    }
}
