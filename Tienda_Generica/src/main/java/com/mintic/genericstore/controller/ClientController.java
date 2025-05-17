package com.mintic.genericstore.controller;

import com.mintic.genericstore.dto.request.ClientRequestDTO;
import com.mintic.genericstore.dto.response.ClientResponseDTO;
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


@RequestMapping(BRANCH_URL + CLIENT_URL)
@CrossOrigin(origins = "*", maxAge = 3600L)
@Tag(name = CLIENT_TAG_NAME_USER, description = CLIENT_TAG_DESCRIPTION_USER)
public interface ClientController {


    @Operation(summary = SUMMARY_GET_ALL_SALE_DETAIL, description = DESC_GET_ALL_PRODUCTS)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = RESP_OK_CLIENTS_RETRIEVED),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = RESP_ERR_INTERNAL_SERVER)
    })
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ClientResponseDTO>> getClients() throws GenericStoreException;

    @Operation(summary = SUMMARY_GET_BY_ID, description = DESC_GET_BY_ID)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = RESP_OK_CLIENT_RETRIEVED),
            @ApiResponse(responseCode = NOT_FOUND, description = RESP_ERR_CLIENT_NOT_FOUND)
    })
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @GetMapping(value = "/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponseDTO> getClient(@PathVariable Long clientId) throws GenericStoreException;

    @Operation(summary = SUMMARY_SAVE, description = DESC_SAVE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = RESP_OK_CLIENT_CREATED),
            @ApiResponse(responseCode = BAD_REQUEST, description = RESP_ERR_BAD_REQUEST)
    })
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponseDTO> save(@Valid @RequestBody ClientRequestDTO client) throws GenericStoreException;

    @Operation(summary = SUMMARY_UPDATE_SALE_DETAIL, description = DESC_UPDATE_PRODUCTS)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = RESP_OK_CLIENT_UPDATED),
            @ApiResponse(responseCode = NOT_FOUND, description = RESP_ERR_CLIENT_NOT_FOUND)
    })
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponseDTO> update(@Valid @RequestBody ClientRequestDTO client) throws GenericStoreException;

    @Operation(summary = SUMMARY_DELETE_SALE_DETAIL, description = DESC_DELETE_PRODUCTS)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = RESP_OK_CLIENT_DELETED),
            @ApiResponse(responseCode = NOT_FOUND, description = RESP_ERR_CLIENT_NOT_FOUND)
    })
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @DeleteMapping(value = "/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponseDTO> delete(@PathVariable Long clientId) throws GenericStoreException;
}
