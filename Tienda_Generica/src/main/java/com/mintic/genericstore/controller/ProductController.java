package com.mintic.genericstore.controller;

import com.mintic.genericstore.dto.request.ProductAllRequestDTO;
import com.mintic.genericstore.dto.request.ProductRequestDTO;
import com.mintic.genericstore.dto.response.ProductResponseDTO;
import com.mintic.genericstore.dto.response.ResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mintic.genericstore.utils.constants.ControllerConstants.*;
import static com.mintic.genericstore.utils.constants.Headers.*;

@RequestMapping(BASE_URL + PRODUCT_URL)
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = PRODUCT_TAG_NAME_USER, description = PRODUCT_TAG_DESCRIPTION_USER)
public interface ProductController {

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get all products",
            description = DESC_GET_ALL_PRODUCTS,
            responses = {
                    @ApiResponse(responseCode = OK, description = OK_ALL,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponseDTO.class))),
                    @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = ERROR_INTERNAL)
            }
    )
    ResponseEntity<List<ProductResponseDTO>> getAllProducts() throws GenericStoreException;

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @GetMapping(value = "/{productCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get product by code",
            description = DESC_GET_ONE_PRODUCTS,
            parameters = @Parameter(name = "productCode", description = "Product code", required = true),
            responses = {
                    @ApiResponse(responseCode = OK, description = OK_ONE,
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponseDTO.class))),
                    @ApiResponse(responseCode = NOT_FOUND, description = ERROR_NOT_FOUND),
                    @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = ERROR_INTERNAL)
            }
    )
    ResponseEntity<ProductResponseDTO> getProduct(@PathVariable("productCode") Long productCode) throws GenericStoreException;

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Create a new product",
            description = DESC_CREATE_PRODUCTS,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product data to create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ProductRequestDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = OK, description = OK_CREATED),
                    @ApiResponse(responseCode = BAD_REQUEST, description = ERROR_BAD_REQUEST),
                    @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = ERROR_INTERNAL)
            }
    )
    ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO product) throws GenericStoreException;

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PostMapping(value = "/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Create multiple products",
            description = DESC_CREATE_ALL_PRODUCTS,
            responses = {
                    @ApiResponse(responseCode = OK, description = OK_CREATED_ALL),
                    @ApiResponse(responseCode = BAD_REQUEST, description = ERROR_BAD_REQUEST),
                    @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = ERROR_INTERNAL)
            }
    )
    ResponseEntity<ResponseDTO> createAllProducts(@Valid @RequestBody ProductAllRequestDTO[] products) throws GenericStoreException;

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Update product",
            description = DESC_UPDATE_PRODUCTS,
            responses = {
                    @ApiResponse(responseCode = OK, description = OK_UPDATED),
                    @ApiResponse(responseCode = BAD_REQUEST, description = ERROR_BAD_REQUEST),
                    @ApiResponse(responseCode = NOT_FOUND, description = ERROR_NOT_FOUND),
                    @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = ERROR_INTERNAL)
            }
    )
    ResponseEntity<ProductResponseDTO> updateProduct(@Valid @RequestBody ProductRequestDTO product) throws GenericStoreException;

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @DeleteMapping(value = "/{productCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Delete product by code",
            description = DESC_DELETE_PRODUCTS,
            parameters = @Parameter(name = "productCode", description = "Product code", required = true),
            responses = {
                    @ApiResponse(responseCode = OK, description = OK_DELETED),
                    @ApiResponse(responseCode = NOT_FOUND, description = ERROR_NOT_FOUND),
                    @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = ERROR_INTERNAL)
            }
    )
    ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable("productCode") Long productCode) throws GenericStoreException;
}
