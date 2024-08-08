package org.example.chatchatpractice.response.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockPriceResponse {
    @JsonProperty("ticker")
    private String ticker;

    @JsonProperty("queryCount")
    private int queryCount;

    @JsonProperty("resultsCount")
    private int resultsCount;

    @JsonProperty("adjusted")
    private boolean adjusted;

    @JsonProperty("results")
    private List<StockPriceResult> results;

    @JsonProperty("status")
    private String status;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("count")
    private int count;

    @Getter
    @Setter
    public static class StockPriceResult {
        @JsonProperty("v")
        private long volume;

        @JsonProperty("vw")
        private double volumeWeightedAveragePrice;

        @JsonProperty("o")
        private double openPrice;

        @JsonProperty("c")
        private double closePrice;

        @JsonProperty("h")
        private double highPrice;

        @JsonProperty("l")
        private double lowPrice;

        @JsonProperty("t")
        private long timestamp;

        @JsonProperty("n")
        private int numberOfTransactions;
    }
}
