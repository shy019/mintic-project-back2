package com.mintic.genericstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetryConfig {

    public static int MAX_ATTEMPTS;
    public static long DELAY;
    public static double MULTIPLIER;
    public static long MAX_DELAY;

    @Value("${mintic.retry.maxAttempts}")
    public void setMaxAttempts(int maxAttempts) {
        RetryConfig.MAX_ATTEMPTS = maxAttempts;
    }

    @Value("${mintic.retry.delay}")
    public void setDelay(long delay) {
        RetryConfig.DELAY = delay;
    }

    @Value("${mintic.retry.multiplier}")
    public void setMultiplier(double multiplier) {
        RetryConfig.MULTIPLIER = multiplier;
    }

    @Value("${mintic.retry.maxDelay}")
    public void setMaxDelay(long maxDelay) {
        RetryConfig.MAX_DELAY = maxDelay;
    }
}
