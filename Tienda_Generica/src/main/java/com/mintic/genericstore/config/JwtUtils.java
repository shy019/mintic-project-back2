package com.mintic.genericstore.config;

import com.mintic.genericstore.model.UserAuthentication;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.mintic.genericstore.utils.constants.ServiceConstants.*;

@Slf4j
@Component
public class JwtUtils {

	@Value("${mintic.app.jwtSecret}")
	private String jwtSecret;

	@Value("${mintic.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {
		UserAuthentication userPrincipal = (UserAuthentication) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUsernameFromJwtToken(String token) {
		return Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			log.error(INVALID_JWT_SIGNATURE, e.getMessage());
		} catch (MalformedJwtException e) {
			log.error(INVALID_JWT_TOKEN, e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error(JWT_TOKEN_EXPIRED, e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error(JWT_TOKEN_UNSUPPORTED, e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error(JWT_EMPTY, e.getMessage());
		}

		return false;
	}
}
