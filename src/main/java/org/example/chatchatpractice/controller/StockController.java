package org.example.chatchatpractice.controller;


import lombok.RequiredArgsConstructor;
import org.example.chatchatpractice.service.StockService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/stocks")
@RestController
public class StockController {

    private final StockService stockService;
}
