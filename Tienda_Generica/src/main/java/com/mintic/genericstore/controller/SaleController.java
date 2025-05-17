package com.mintic.genericstore.controller;

import com.mintic.genericstore.dto.request.SaleRequestDTO;
import com.mintic.genericstore.dto.response.SaleResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@RequestMapping(BASE_URL + SALE_URL)
@CrossOrigin(origins = "*", maxAge = 3600L)
@Tag(name = SALE_TAG_NAME, description = SALE_TAG_DESCRIPTION)
public interface SaleController {

    @Operation(summary = GET_ALL_SALES_SUMMARY, description = GET_ALL_SALES_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = RESPONSE_OK),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = RESPONSE_INTERNAL_ERROR)
    })
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<SaleResponseDTO>> getAllSales() throws GenericStoreException;

    @Operation(summary = GET_SALE_BY_ID_SUMMARY, description = GET_SALE_BY_ID_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = RESPONSE_OK),
            @ApiResponse(responseCode = NOT_FOUND, description = RESPONSE_NOT_FOUND),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = RESPONSE_INTERNAL_ERROR)
    })
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @GetMapping(value = "/{saleCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SaleResponseDTO> getSaleById(
            @Parameter(description = "Sale code", required = true)
            @PathVariable("saleCode") Long saleCode) throws GenericStoreException;

    @Operation(summary = CREATE_SALE_SUMMARY, description = CREATE_SALE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = RESPONSE_CREATED),
            @ApiResponse(responseCode = BAD_REQUEST, description = RESPONSE_BAD_REQUEST),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = RESPONSE_INTERNAL_ERROR)
    })
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SaleResponseDTO> createSale(
            @Parameter(description = "Sale data", required = true)
            @Valid @RequestBody SaleRequestDTO sale) throws GenericStoreException;

    @Operation(summary = UPDATE_SALE_SUMMARY, description = UPDATE_SALE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = RESPONSE_UPDATED),
            @ApiResponse(responseCode = BAD_REQUEST, description = RESPONSE_BAD_REQUEST),
            @ApiResponse(responseCode = NOT_FOUND, description = RESPONSE_NOT_FOUND),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = RESPONSE_INTERNAL_ERROR)
    })
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SaleResponseDTO> updateSale(
            @Parameter(description = "Updated sale data", required = true)
            @RequestBody SaleRequestDTO sale) throws GenericStoreException;

    @Operation(summary = DELETE_SALE_SUMMARY, description = DELETE_SALE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = RESPONSE_DELETED),
            @ApiResponse(responseCode = NOT_FOUND, description = RESPONSE_NOT_FOUND),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = RESPONSE_INTERNAL_ERROR)
    })
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @DeleteMapping(value = "/{saleCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SaleResponseDTO> deleteSale(
            @Parameter(description = "Sale code", required = true)
            @PathVariable("saleCode") Long saleCode) throws GenericStoreException;
}
