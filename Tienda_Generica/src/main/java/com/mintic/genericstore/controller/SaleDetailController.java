package com.mintic.genericstore.controller;

import com.mintic.genericstore.dto.request.SaleDetailRequestDTO;
import com.mintic.genericstore.dto.response.SaleDetailResponseDTO;
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

@RequestMapping(BASE_URL + SALE_DETAIL_URL)
@CrossOrigin(origins = "*", maxAge = 3600L)
@Tag(name = SALE_DETAIL_TAG_NAME, description = SALE_DETAIL_TAG_DESCRIPTION)
public interface SaleDetailController {

    @Operation(summary = SUMMARY_GET_ALL_SALE_DETAIL, description = DESC_GET_ALL_SALE_DETAIL)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = APIRES_OK_GET_ALL_SALE_DETAIL),
            @ApiResponse(responseCode = BAD_REQUEST, description = APIRES_BADREQ_GET_ALL_SALE_DETAIL),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_SALE_DETAIL)
    })
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<SaleDetailResponseDTO>> getAllSaleDetails() throws GenericStoreException;

    @Operation(summary = SUMMARY_GET_BY_CODE_SALE_DETAIL, description = DESC_GET_BY_CODE_SALE_DETAIL)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = APIRES_OK_GET_BY_CODE_SALE_DETAIL),
            @ApiResponse(responseCode = NOT_FOUND, description = APIRES_NOT_FOUND_SALE_DETAIL),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_SALE_DETAIL)
    })
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @GetMapping(value = "/{saleCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SaleDetailResponseDTO> getSaleDetailBySaleCode(
            @Parameter(description = "Sale code") @PathVariable("saleCode") Long saleCode)
            throws GenericStoreException;

    @Operation(summary = SUMMARY_CREATE_SALE_DETAIL, description = DESC_CREATE_SALE_DETAIL)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = APIRES_OK_CREATE_SALE_DETAIL),
            @ApiResponse(responseCode = BAD_REQUEST, description = APIRES_BADREQ_CREATE_SALE_DETAIL),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_SALE_DETAIL)
    })
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> saveAllSaleDetails(@Valid @RequestBody SaleDetailRequestDTO[] saleDetails) throws GenericStoreException;

    @Operation(summary = SUMMARY_UPDATE_SALE_DETAIL, description = DESC_UPDATE_SALE_DETAIL)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = APIRES_OK_UPDATE_SALE_DETAIL),
            @ApiResponse(responseCode = NOT_FOUND, description = APIRES_NOT_FOUND_SALE_DETAIL),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_SALE_DETAIL)
    })
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SaleDetailResponseDTO> updateSaleDetail(@RequestBody SaleDetailRequestDTO saleDetail) throws GenericStoreException;

    @Operation(summary = SUMMARY_DELETE_SALE_DETAIL, description = DESC_DELETE_SALE_DETAIL)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = APIRES_OK_DELETE_SALE_DETAIL),
            @ApiResponse(responseCode = NOT_FOUND, description = APIRES_NOT_FOUND_SALE_DETAIL),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_SALE_DETAIL)
    })
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @DeleteMapping(value = "/{saleCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SaleDetailResponseDTO> deleteSaleDetail(
            @Parameter(description = "Sale code") @PathVariable("saleCode") Long saleCode)
            throws GenericStoreException;
}
