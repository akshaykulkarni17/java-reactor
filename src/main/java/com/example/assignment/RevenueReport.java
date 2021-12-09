package com.example.assignment;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class RevenueReport {

    private LocalDateTime localDateTime ;
    private Map<String,Double> revenue;

    public RevenueReport(Map<String, Double> revenue) {
        this.localDateTime = LocalDateTime.now();
        this.revenue = revenue;
    }
}
