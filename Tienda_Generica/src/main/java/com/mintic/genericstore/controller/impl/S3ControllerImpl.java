package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.controller.S3Controller;
import com.mintic.genericstore.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@RestController
public class S3ControllerImpl implements S3Controller {

    @Autowired
    private S3Service s3Service;

    @Override
    public ResponseEntity<String> createBucket(String bucketName) {
        return ResponseEntity.ok(s3Service.createBucket(bucketName));
    }

    @Override
    public ResponseEntity<String> checkBucket(String bucketName) {
        return ResponseEntity.ok(s3Service.checkIfBucketExist(bucketName));
    }

    @Override
    public ResponseEntity<List<String>> listBuckets() {
        return ResponseEntity.ok(s3Service.getAllBuckets());
    }

    @Override
    public ResponseEntity<String> uploadFile(String bucketName, String key, MultipartFile file) throws IOException {
        boolean result = s3Service.uploadFile(bucketName, key, file);

        if (result) {
            return ResponseEntity.ok("File uploaded successfully");
        } else {
            return ResponseEntity.internalServerError().body("Error uploading file");
        }
    }

    @Override
    public ResponseEntity<String> downloadFile(String bucketName, String key) throws IOException {
        s3Service.downloadFile(bucketName, key);
        return ResponseEntity.ok("File downloaded successfully");
    }

    @Override
    public ResponseEntity<String> generatePresignedUploadUrl(String bucketName, String key, Long time) {
        return ResponseEntity.ok(s3Service.generatePresignedUploadUrl(bucketName, key, Duration.ofMinutes(time)));
    }

    @Override
    public ResponseEntity<String> generatePresignedDownloadUrl(String bucketName, String key, Long time) {
        return ResponseEntity.ok(s3Service.generatePresignedDownloadUrl(bucketName, key, Duration.ofMinutes(time)));
    }
}
