package com.mintic.genericstore.controller;

import com.mintic.genericstore.dto.request.SignupRequestDTO;
import com.mintic.genericstore.dto.response.UserResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mintic.genericstore.utils.constants.ControllerConstants.*;
import static com.mintic.genericstore.utils.constants.Headers.*;

@RestController
@RequestMapping(BASE_URL + USER_URL)
@CrossOrigin(origins = "*", maxAge = 3600L)
@Tag(name = USER_TAG_NAME_USER, description = USER_TAG_DESCRIPTION_USER)
public interface UserController {

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = SUMMARY_GET_ALL_USER, description = DESC_GET_ALL_USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = APIRES_OK_GET_ALL_USER),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_USER)
    })
    ResponseEntity<List<UserResponseDTO>> getUsers() throws GenericStoreException;

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = SUMMARY_GET_BY_ID_USER, description = DESC_GET_BY_ID_USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = APIRES_OK_GET_BY_ID_USER),
            @ApiResponse(responseCode = NOT_FOUND, description = APIRES_NOT_FOUND_USER),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_USER)
    })
    ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") Long id) throws GenericStoreException;

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = SUMMARY_CREATE_USER, description = DESC_CREATE_USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = APIRES_OK_CREATE_USER),
            @ApiResponse(responseCode = BAD_REQUEST, description = APIRES_BADREQ_CREATE_USER),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_USER)
    })
    ResponseEntity<UserResponseDTO> save(@Valid @RequestBody SignupRequestDTO user) throws GenericStoreException;

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = SUMMARY_UPDATE_USER, description = DESC_UPDATE_USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = APIRES_OK_UPDATE_USER),
            @ApiResponse(responseCode = BAD_REQUEST, description = APIRES_BADREQ_CREATE_USER),
            @ApiResponse(responseCode = NOT_FOUND, description = APIRES_NOT_FOUND_USER),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_USER)
    })
    ResponseEntity<UserResponseDTO> update(@RequestBody SignupRequestDTO user) throws GenericStoreException;

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = SUMMARY_DELETE_USER, description = DESC_DELETE_USER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = APIRES_OK_DELETE_USER),
            @ApiResponse(responseCode = NOT_FOUND, description = APIRES_NOT_FOUND_USER),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_USER)
    })
    ResponseEntity<UserResponseDTO> delete(@PathVariable("id") long id) throws GenericStoreException;
}
