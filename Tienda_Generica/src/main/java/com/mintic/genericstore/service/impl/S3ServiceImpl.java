package com.mintic.genericstore.service.impl;

import com.mintic.genericstore.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.time.Duration;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    @Value("${spring.destination.folder}")
    String destinationFolder;

    private S3Client s3Client;

    private S3Presigner s3Presigner;

    @Override
    public String createBucket(String bucketName) {
        CreateBucketResponse response = this.s3Client.createBucket(bucketBuilder -> bucketBuilder.bucket(bucketName));
        return "Bucket created at: " + response.location();
    }

    @Override
    public String checkIfBucketExist(String bucketName) {
        try {
            this.s3Client.headBucket(headBucket -> headBucket.bucket(bucketName));
            return "Bucket " + bucketName + " exists.";
        } catch (S3Exception exception) {
            return "Bucket " + bucketName + " doesn't exist.";
        }
    }

    @Override
    public List<String> getAllBuckets() {
        ListBucketsResponse bucketsResponse = this.s3Client.listBuckets();
        return bucketsResponse.hasBuckets()
                ? bucketsResponse.buckets().stream().map(Bucket::name).toList()
                : List.of();
    }

    @Override
    public Boolean uploadFile(String bucketName, String key, Path fileLocation) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        PutObjectResponse response = this.s3Client.putObject(putObjectRequest, fileLocation);
        return response.sdkHttpResponse().isSuccessful();
    }

    @Override
    public void downloadFile(String bucket, String key) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        ResponseBytes<GetObjectResponse> objectBytes = this.s3Client.getObjectAsBytes(getObjectRequest);

        String filename = key.contains("/") ? key.substring(key.lastIndexOf("/") + 1) : key;
        String fileTypeFolder = detectFileTypeFolder(filename);

        Path destinationDir = Paths.get(destinationFolder, fileTypeFolder);
        Files.createDirectories(destinationDir);

        Path destinationPath = destinationDir.resolve(filename);
        try (FileOutputStream fos = new FileOutputStream(destinationPath.toFile())) {
            fos.write(objectBytes.asByteArray());
        } catch (IOException e) {
            throw new IOException("Error when trying to write the file locally", e);
        }
    }

    @Override
    public String generatePresignedUploadUrl(String bucketName, String key, Duration duration) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(duration)
                .putObjectRequest(putObjectRequest)
                .build();

        PresignedPutObjectRequest presignedRequest = this.s3Presigner.presignPutObject(presignRequest);
        return presignedRequest.url().toString();
    }

    @Override
    public String generatePresignedDownloadUrl(String bucketName, String key, Duration duration) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(duration)
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedRequest = this.s3Presigner.presignGetObject(presignRequest);
        return presignedRequest.url().toString();
    }

    @Override
    public boolean uploadFile(String bucketName, String key, MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String fileTypeFolder = detectFileTypeFolder(filename);

        Path tempDir = Paths.get(destinationFolder, "temp");
        Files.createDirectories(tempDir);

        Path tempFilePath = tempDir.resolve(filename);
        Files.write(tempFilePath, file.getBytes());

        try {
            boolean result = uploadFile(bucketName, key, tempFilePath);
            Files.deleteIfExists(tempFilePath);
            return result;
        } catch (Exception e) {
            Files.deleteIfExists(tempFilePath);
            throw new IOException("Error uploading file to S3", e);
        }
    }

    private String detectFileTypeFolder(String filename) {
        String extension = "";

        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex >= 0) {
            extension = filename.substring(dotIndex + 1).toLowerCase();
        }

        return switch (extension) {
            case "jpg", "jpeg", "png", "gif" -> "images";
            case "pdf" -> "pdfs";
            case "doc", "docx" -> "documents";
            case "mp4", "mov", "avi" -> "videos";
            default -> "others";
        };
    }
}
