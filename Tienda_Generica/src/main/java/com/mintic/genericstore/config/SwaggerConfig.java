package com.mintic.genericstore.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	public static final String SECURITY_SCHEME_NAME = "BearerAuth";

	@Bean
	public OpenAPI baseOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("GenericStore API")
						.version("1.0")
						.description("API documentation for the GenericStore application")
						.contact(new Contact()
								.name("Mintic Team")
								.email("support@mintic.com")
								.url("https://www.mintic.gov.co")
						)
				)
				// Define JWT Bearer security scheme
				.components(new Components()
						.addSecuritySchemes(SECURITY_SCHEME_NAME,
								new SecurityScheme()
										.name("Authorization")
										.type(SecurityScheme.Type.HTTP)
										.scheme("bearer")
										.bearerFormat("JWT")
						)
				)
				// Apply security scheme globally
				.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
	}

	@Bean
	public GroupedOpenApi apiGroup() {
		return GroupedOpenApi.builder()
				.group("api")
				.pathsToMatch("/api/**")
				.build();
	}
}
