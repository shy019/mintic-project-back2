package com.mintic.genericstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.mintic.genericstore.utils.constants.ControllerConstants.*;

@RequestMapping(BASE_URL + "s3")
@CrossOrigin(origins = "*", maxAge = 3600L)
@Tag(name = CONTROLLER_TAG_NAME, description = CONTROLLER_TAG_DESCRIPTION)
public interface S3Controller {

    @Operation(summary = CREATE_BUCKET_SUMMARY, description = CREATE_BUCKET_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = RESPONSE_200_BUCKET_CREATED),
            @ApiResponse(responseCode = "500", description = RESPONSE_500_ERROR)
    })
    @PostMapping(CREATE_PATH)
    ResponseEntity<String> createBucket(
            @Parameter(description = PARAM_BUCKET_NAME_CREATE, required = true)
            @RequestParam String bucketName
    );

    @Operation(summary = CHECK_BUCKET_SUMMARY, description = CHECK_BUCKET_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = RESPONSE_200_BUCKET_CHECK),
            @ApiResponse(responseCode = "500", description = RESPONSE_500_ERROR)
    })
    @GetMapping(CHECK_PATH)
    ResponseEntity<String> checkBucket(
            @Parameter(description = PARAM_BUCKET_NAME, required = true)
            @PathVariable String bucketName
    );

    @Operation(summary = LIST_BUCKETS_SUMMARY, description = LIST_BUCKETS_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = RESPONSE_200_BUCKET_LISTED),
            @ApiResponse(responseCode = "500", description = RESPONSE_500_ERROR)
    })
    @GetMapping(LIST_PATH)
    ResponseEntity<List<String>> listBuckets();

    @Operation(summary = UPLOAD_FILE_SUMMARY, description = UPLOAD_FILE_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = RESPONSE_200_FILE_UPLOADED),
            @ApiResponse(responseCode = "500", description = RESPONSE_500_UPLOAD_ERROR)
    })
    @PostMapping(UPLOAD_PATH)
    ResponseEntity<String> uploadFile(
            @Parameter(description = PARAM_BUCKET_NAME, required = true)
            @RequestParam String bucketName,

            @Parameter(description = PARAM_KEY, required = true)
            @RequestParam String key,

            @Parameter(description = PARAM_FILE, required = true)
            @RequestPart MultipartFile file
    ) throws IOException;

    @Operation(summary = DOWNLOAD_FILE_SUMMARY, description = DOWNLOAD_FILE_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = RESPONSE_200_FILE_DOWNLOADED),
            @ApiResponse(responseCode = "500", description = RESPONSE_500_DOWNLOAD_ERROR)
    })
    @PostMapping(DOWNLOAD_PATH)
    ResponseEntity<String> downloadFile(
            @Parameter(description = PARAM_BUCKET_NAME, required = true)
            @RequestParam String bucketName,

            @Parameter(description = PARAM_KEY, required = true)
            @RequestParam String key
    ) throws IOException;

    @Operation(summary = PRESIGNED_UPLOAD_SUMMARY, description = PRESIGNED_UPLOAD_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = RESPONSE_200_PRESIGNED_UPLOAD),
            @ApiResponse(responseCode = "500", description = RESPONSE_500_PRESIGNED_ERROR)
    })
    @PostMapping(PRESIGNED_UPLOAD_PATH)
    ResponseEntity<String> generatePresignedUploadUrl(
            @Parameter(description = PARAM_BUCKET_NAME, required = true)
            @RequestParam String bucketName,

            @Parameter(description = PARAM_KEY, required = true)
            @RequestParam String key,

            @Parameter(description = PARAM_TIME, required = true)
            @RequestParam Long time
    );

    @Operation(summary = PRESIGNED_DOWNLOAD_SUMMARY, description = PRESIGNED_DOWNLOAD_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = RESPONSE_200_PRESIGNED_DOWNLOAD),
            @ApiResponse(responseCode = "500", description = RESPONSE_500_PRESIGNED_ERROR)
    })
    @PostMapping(PRESIGNED_DOWNLOAD_PATH)
    ResponseEntity<String> generatePresignedDownloadUrl(
            @Parameter(description = PARAM_BUCKET_NAME, required = true)
            @RequestParam String bucketName,

            @Parameter(description = PARAM_KEY, required = true)
            @RequestParam String key,

            @Parameter(description = PARAM_TIME, required = true)
            @RequestParam Long time
    );
}
