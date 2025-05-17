package com.mintic.genericstore.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.mintic.genericstore.utils.constants.ServiceConstants.UNAUTHORIZED_ERROR;
import static com.mintic.genericstore.utils.constants.ServiceConstants.UNAUTHORIZED_ERROR_2;

@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) throws IOException {
		log.error(UNAUTHORIZED_ERROR, authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, UNAUTHORIZED_ERROR_2);
	}
}
