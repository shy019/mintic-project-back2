package com.mintic.genericstore.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

public interface S3Service {

    String createBucket(String bucketName);

    String checkIfBucketExist(String bucketName);

    List<String> getAllBuckets();

    Boolean uploadFile(String bucketName, String key, Path fileLocation);

    void downloadFile(String bucket, String key) throws IOException;

    String generatePresignedUploadUrl(String bucketName, String key, Duration duration);

    String generatePresignedDownloadUrl(String bucketName, String key, Duration duration);

    boolean uploadFile(String bucketName, String key, MultipartFile file) throws IOException;
}
