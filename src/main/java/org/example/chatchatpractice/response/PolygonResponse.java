package org.example.chatchatpractice.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PolygonResponse {
    private String ticker;
    private int queryCount;
    private int resultsCount;
    private boolean adjusted;
    private List<Result> results;
    private String status;
    @JsonProperty("request_id")
    private String requestId;
    private int count;

    public static class Result {
        private double c;
        private double h;
        private double l;
        private long n;
        private double o;
        private long t;
        private long v;
        private double vw;
    }
}
