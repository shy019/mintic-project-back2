package com.mintic.genericstore.controller;

import com.mintic.genericstore.dto.request.BranchRequestDTO;
import com.mintic.genericstore.dto.response.BranchResponseDTO;
import com.mintic.genericstore.dto.response.SaleByBranchResponseDTO;
import com.mintic.genericstore.exception.GenericStoreException;
import com.mintic.genericstore.utils.constants.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.mintic.genericstore.utils.constants.ControllerConstants.*;
import static com.mintic.genericstore.utils.constants.Headers.*;

@RequestMapping(BASE_URL + BRANCH_URL)
@CrossOrigin(origins = "*", maxAge = 3600L)
@Tag(name = BRANCH_TAG_NAME_USER, description = BRANCH_TAG_DESCRIPTION_USER)
public interface BranchController {

    @Operation(
            summary = ControllerConstants.SAVE_BRANCH_SUMMARY,
            description = ControllerConstants.SAVE_BRANCH_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = ControllerConstants.SAVE_BRANCH_200,
                    content = @Content(schema = @Schema(implementation = BranchResponseDTO.class))),
            @ApiResponse(responseCode = BAD_REQUEST, description = ControllerConstants.SAVE_BRANCH_400, content = @Content),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = ControllerConstants.SAVE_BRANCH_500, content = @Content)
    })
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BranchResponseDTO> saveBranch(@Valid @RequestBody BranchRequestDTO branchDTO) throws GenericStoreException;

    @Operation(
            summary = ControllerConstants.GET_SALES_BY_BRANCH_SUMMARY,
            description = ControllerConstants.GET_SALES_BY_BRANCH_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = OK, description = ControllerConstants.GET_SALES_BY_BRANCH_200,
                    content = @Content(schema = @Schema(implementation = SaleByBranchResponseDTO.class))),
            @ApiResponse(responseCode = BAD_REQUEST, description = ControllerConstants.GET_SALES_BY_BRANCH_400, content = @Content),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = ControllerConstants.GET_SALES_BY_BRANCH_500, content = @Content)
    })
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PostMapping(value = "/sales", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<SaleByBranchResponseDTO>> getSalesByBranch(@Valid @RequestBody BranchRequestDTO branchDTO) throws GenericStoreException;
}
