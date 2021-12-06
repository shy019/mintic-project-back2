package com.mintic.tiendagenerica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

//	@Bean
//	public Docket apiDocket() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.mintic.tiendagenerica.controller"))
//				.paths(PathSelectors.any()).build().apiInfo(getApiInfo());
//	}
//
//	private ApiInfo getApiInfo() {
//		return new ApiInfo("Order Service API", "Order Service API Description", "1.0", "http://codmind.com/terms",
//				new Contact("Grupo 50 - 1", "http://54.145.7.165:8080/mintic/views/Inicio.html",
//						"cristianpg123@hotmail..com"),
//				"LICENSE", "LICENSE URL", Collections.emptyList());
//	}

	public static final String AUTHORIZATION_HEADER = "tiendaGenericaV2";

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Spring Boot Blog REST APIs", "Spring Boot Blog REST API Documentation", "1",
				"Terms of service", new Contact("Ramesh Fadatare", "www.javaguides.net", "ramesh@gmail.com"),
				"License of API", "API license URL", Collections.emptyList());
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(apiKey())).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}
}
