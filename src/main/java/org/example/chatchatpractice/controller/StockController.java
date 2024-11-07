package org.example.chatchatpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.chatchatpractice.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class StockController {

    private final StockService stockService;

    @GetMapping("/stock")
    public String stock() {
        return "stock";
    }

    @GetMapping("/stock-detail")
    public String stockDetail() {
        return "stock-detail";
    }

}
