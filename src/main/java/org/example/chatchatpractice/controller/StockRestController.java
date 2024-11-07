package org.example.chatchatpractice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatchatpractice.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stock")
public class StockRestController {

    private final StockService stockService;

    @GetMapping("/price-info")
    public Mono<String> getStockPriceInfo(@RequestParam String itmsNm) {
        log.info("start getStockPriceInfo : {}", itmsNm);
        return stockService.getStockInfo(itmsNm);
    }

    @GetMapping("/test123")
    public String test123() {
        return "1234";
    }
}
