package org.example.chatchatpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.chatchatpractice.response.response.StockPriceResponse;
import org.example.chatchatpractice.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class StockController {

    private final StockService stockService;

    @GetMapping("/stock")
    public String stock() {
        return "stock";
    }

    @PostMapping("/search")
    public String searchStock(@RequestParam String symbol, Model model) {
        try {
            StockPriceResponse stockData = stockService.getStockPrice(symbol);
            model.addAttribute("stockData", stockData);
            model.addAttribute("symbol", symbol);
            return "result";  // This will render the result.html template
        } catch (Exception e) {
            model.addAttribute("error", "Unable to fetch stock data: " + e.getMessage());
            return "main";  // Return to main page with error message
        }
    }
}
