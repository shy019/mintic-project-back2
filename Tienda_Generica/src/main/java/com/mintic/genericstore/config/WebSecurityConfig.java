package com.mintic.genericstore.config;

import com.mintic.genericstore.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final AuthEntryPointJwt authEntryPointJwt;

	@Bean
	public AuthTokenFilter authTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsServiceImpl);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// CORS + CSRF
				.cors(Customizer.withDefaults())
				.csrf(csrf -> csrf.disable())

				// Error handling & stateless session
				.exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPointJwt))
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// Authorization rules
				.authorizeHttpRequests(auth -> auth
						// Public endpoints
						.requestMatchers(
								"/api/generic-store/auth/signup",
								"/api/generic-store/auth/signin",
								"/swagger-ui.html",
								"/v3/api-docs/**",
								"/swagger-ui/**"
						).permitAll()

						// Actuator: health & info public
						.requestMatchers("/actuator/health", "/actuator/info", "/actuator/prometheus").permitAll()

						// Actuator: other endpoints (metrics, prometheus, beans, env, loggers) require authentication
						.requestMatchers("/actuator/**").authenticated()

						// All other requests require auth
						.anyRequest().authenticated()
				)

				// Authentication provider + JWT filter
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
