package com.mintic.genericstore.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRateResponseDTO {
    private String base;
    private String date;
    private Map<String, Double> rates;
}