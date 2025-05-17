package com.mintic.genericstore.controller;

import com.mintic.genericstore.dto.request.LoginRequestDTO;
import com.mintic.genericstore.dto.request.SignupRequestDTO;
import com.mintic.genericstore.dto.response.JwtResponse;
import com.mintic.genericstore.dto.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mintic.genericstore.utils.constants.ControllerConstants.*;
import static com.mintic.genericstore.utils.constants.ControllerConstants.AUTHENTICATION_DESCRIPTION;
import static com.mintic.genericstore.utils.constants.Headers.*;
import static com.mintic.genericstore.utils.constants.ServiceConstants.*;
import static com.mintic.genericstore.utils.constants.ServiceConstants.USER_OR_EMAIL_IS_TAKEN;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(BASE_URL + AUTH_URL)
@Tag(name = AUTHENTICATION, description = AUTHENTICATION_DESCRIPTION)
public interface AuthController {

	@Operation(
			summary = AUTHENTICATION_SUMMARY,
			description = AUTHENTICATION_INTERNAL_DESCRIPTION
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = OK, description = AUTHENTICATION_OK,
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = JwtResponse.class))),
			@ApiResponse(responseCode = UNAUTHORIZED, description = AUTHENTICATION_INVALID_CREDENTIALS, content = @Content),
			@ApiResponse(responseCode = BAD_REQUEST, description = APIRES_BADREQ_GET_ALL_SALE_DETAIL, content = @Content)
	})
	@PostMapping("/signin")
	ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO);

	@Operation(
			summary = REGISTER_NEW_USER,
			description = REGISTER_DESCRIPTION
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = OK, description = USER_REGISTERED,
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponse.class))),
			@ApiResponse(responseCode = BAD_REQUEST, description = USER_OR_EMAIL_IS_TAKEN, content = @Content)
	})
	@PostMapping("/signup")
	ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequest);
}
