package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.dto.request.ExchangeRateResponseDTO;
import com.mintic.genericstore.exception.ExchangeRateException;
import com.mintic.genericstore.service.ExchangeRateService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

import static com.mintic.genericstore.utils.constants.ControllerConstants.*;
import static com.mintic.genericstore.utils.constants.ServiceConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final WebClient webClient;

    @Override
    @Retryable(
            maxAttemptsExpression = "${mintic.retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${mintic.retry.delay}", multiplierExpression = "${mintic.retry.multiplier}")
    )
    @CircuitBreaker(name = "exchangeRateCB", fallbackMethod = "fallbackDollarToPesoRate")
    public Double getDollarToPesoRate() {
        log.info(FETCHING_EXCHANGE_RATE);

        ExchangeRateResponseDTO response = webClient
                .get()
                .uri(CONCURRENCY_TYPE_USD)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        clientResponse -> clientResponse.bodyToMono(String.class).map(body -> {
                            log.error(CLIENT_ERROR_HANDLE_4XX, clientResponse.statusCode(), body);
                            return new ExchangeRateException(CLIENT_ERROR_MESSAGE + body);
                        })
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        clientResponse -> clientResponse.bodyToMono(String.class).map(body -> {
                            log.error(CLIENT_ERROR_HANDLE_5XX, clientResponse.statusCode(), body);
                            return new ExchangeRateException(CLIENT_ERROR_MESSAGE + body);
                        })
                )
                .bodyToMono(ExchangeRateResponseDTO.class)
                .doOnNext(this::validateResponse)
                .block();

        Double rate = response.getRates().get(CONCURRENCY_TYPE_COP);
        log.info(FETCHING_SUCCESSFULLY, rate);
        return rate;
    }

    private void validateResponse(ExchangeRateResponseDTO response) {
        if (Objects.isNull(response)) {
            log.error(FETCHING_NULL);
            throw new ExchangeRateException(FETCHING_NULL);
        }

        if (Objects.isNull(response.getRates()) || !response.getRates().containsKey(CONCURRENCY_TYPE_COP)) {
            log.error(CONCURRENCY_ERROR_MESSAGE);
            throw new ExchangeRateException(CONCURRENCY_ERROR_MESSAGE);
        }
    }

    public Double fallbackDollarToPesoRate(Throwable t) {
        log.error(SERVICE_DOWN);
        return CONCURRENCY_RATE;
    }
}
