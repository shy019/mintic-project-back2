package com.mintic.genericstore.controller;

import com.mintic.genericstore.dto.request.SupplierRequestDTO;
import com.mintic.genericstore.dto.response.SupplierResponseDTO;
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
@RequestMapping(BASE_URL + SUPPLIER_URL)
@CrossOrigin(origins = "*", maxAge = 3600L)
@Tag(name = SUPPLIER_TAG_NAME_SUPPLIER, description = SUPPLIER_TAG_DESCRIPTION_SUPPLIER)
public interface SupplierController {

    @Operation(summary = SUMMARY_GET_ALL_SUPPLIER, description = DESC_GET_ALL_SUPPLIER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = APIRES_OK_GET_ALL_SUPPLIER),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_SUPPLIER)
    })
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<SupplierResponseDTO>> getSuppliers() throws GenericStoreException;

    @Operation(summary = SUMMARY_GET_BY_NIT_SUPPLIER, description = DESC_GET_BY_NIT_SUPPLIER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = APIRES_OK_GET_BY_NIT_SUPPLIER),
            @ApiResponse(responseCode = NOT_FOUND, description = APIRES_NOT_FOUND_SUPPLIER),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_SUPPLIER)
    })
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @RequestMapping(value = "/{SupplierId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SupplierResponseDTO> getSupplier(@PathVariable("SupplierId") Long SupplierId)
            throws GenericStoreException;

    @Operation(summary = SUMMARY_CREATE_SUPPLIER, description = DESC_CREATE_SUPPLIER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = APIRES_OK_CREATE_SUPPLIER),
            @ApiResponse(responseCode = BAD_REQUEST, description = APIRES_BADREQ_CREATE_SUPPLIER),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_SUPPLIER)
    })
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SupplierResponseDTO> save(@Valid @RequestBody SupplierRequestDTO supplier)
            throws GenericStoreException;

    @Operation(summary = SUMMARY_UPDATE_SUPPLIER, description = DESC_UPDATE_SUPPLIER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = APIRES_OK_UPDATE_SUPPLIER),
            @ApiResponse(responseCode = NOT_FOUND, description = APIRES_NOT_FOUND_SUPPLIER),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_SUPPLIER)
    })
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SupplierResponseDTO> update(@RequestBody SupplierRequestDTO supplier)
            throws GenericStoreException;

    @Operation(summary = SUMMARY_DELETE_SUPPLIER, description = DESC_DELETE_SUPPLIER)
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = APIRES_OK_DELETE_SUPPLIER),
            @ApiResponse(responseCode = NOT_FOUND, description = APIRES_NOT_FOUND_SUPPLIER),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = APIRES_INTERNAL_ERR_SUPPLIER)
    })
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @RequestMapping(value = "/{SupplierId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SupplierResponseDTO> delete(@PathVariable("SupplierId") Long SupplierId)
            throws GenericStoreException;
}
