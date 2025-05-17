package com.mintic.genericstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
@EnableCaching
public class GenericStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenericStoreApplication.class, args);
	}

}
