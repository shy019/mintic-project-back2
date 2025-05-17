package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.ExchangeRateResponseDTO;
import com.mintic.genericstore.exception.ExchangeRateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

import static com.mintic.genericstore.utils.constants.ServiceConstants.CONCURRENCY_RATE;
import static com.mintic.genericstore.utils.constants.ServiceConstants.CONCURRENCY_TYPE_COP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ExchangeRateServiceImplTest {

    private WebClient webClient;
    private ExchangeRateServiceImpl service;

    private WebClient.RequestHeadersUriSpec<?> uriSpec;
    private WebClient.RequestHeadersSpec<?> headersSpec;
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        webClient = mock(WebClient.class);
        uriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        headersSpec = mock(WebClient.RequestHeadersSpec.class);
        responseSpec = mock(WebClient.ResponseSpec.class);

        doReturn(uriSpec).when(webClient).get();
        doReturn(headersSpec).when(uriSpec).uri(anyString());
        doReturn(responseSpec).when(headersSpec).retrieve();
        doReturn(responseSpec).when(responseSpec).onStatus(any(), any());

        service = new ExchangeRateServiceImpl(webClient);
    }

    @Test
    void getDollarToPesoRate_success() {
        ExchangeRateResponseDTO dto = new ExchangeRateResponseDTO();
        dto.setRates(Map.of(CONCURRENCY_TYPE_COP, 4200.0));
        doReturn(Mono.just(dto))
                .when(responseSpec).bodyToMono(ExchangeRateResponseDTO.class);

        Double rate = service.getDollarToPesoRate();
        assertEquals(4200.0, rate);
    }

    @Test
    void getDollarToPesoRate_nullResponse_throwsException() {
        doReturn(Mono.empty())
                .when(responseSpec).bodyToMono(ExchangeRateResponseDTO.class);

        assertThrows(NullPointerException.class, () -> service.getDollarToPesoRate());
    }

    @Test
    void getDollarToPesoRate_missingRate_throwsException() {
        ExchangeRateResponseDTO dto = new ExchangeRateResponseDTO();
        dto.setRates(Map.of("USD", 1.0));
        doReturn(Mono.just(dto))
                .when(responseSpec).bodyToMono(ExchangeRateResponseDTO.class);

        assertThrows(ExchangeRateException.class, () -> service.getDollarToPesoRate());
    }

    @Test
    void fallbackDollarToPesoRate_returnsDefault() {
        Double fallback = service.fallbackDollarToPesoRate(new RuntimeException("any"));
        assertEquals(CONCURRENCY_RATE, fallback);
    }
}
