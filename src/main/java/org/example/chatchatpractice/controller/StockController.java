package org.example.chatchatpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.chatchatpractice.response.PolygonResponse;
import org.example.chatchatpractice.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class StockController {

    private final StockService stockService;

    @GetMapping("/stock")
    public String stock() {
        return "stock";
    }

    @GetMapping("/stock-detail/{ticker}")
    public String getStockData(@PathVariable String ticker, Model model) {
        var StockData = stockService.getStockData(ticker);
        //model.addAttribute("stockData", stockData);
        return "stock-result";
    }
}
