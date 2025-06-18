package com.mintic.genericstore.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class S3ServiceImplTest {

    @InjectMocks
    private S3ServiceImpl s3Service;

    @Mock
    private S3Client s3Client;

    @Mock
    private S3Presigner s3Presigner;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        s3Service.destinationFolder = tempDir.toString();
    }

    @Test
    void createBucket_shouldReturnLocationMessage() {
        CreateBucketResponse response = CreateBucketResponse.builder()
                .location("/test-location")
                .build();

        when(s3Client.createBucket(any(Consumer.class))).thenAnswer(invocation -> {
            Consumer<CreateBucketRequest.Builder> consumer = invocation.getArgument(0);
            CreateBucketRequest.Builder builder = CreateBucketRequest.builder();
            consumer.accept(builder);
            return response;
        });

        String result = s3Service.createBucket("test-bucket");
        assertEquals("Bucket created at: /test-location", result);
    }

    @Test
    void checkIfBucketExist_shouldReturnExistsMessage() {
        HeadBucketResponse response = HeadBucketResponse.builder().build();

        when(s3Client.headBucket(any(Consumer.class))).thenAnswer(invocation -> {
            Consumer<HeadBucketRequest.Builder> consumer = invocation.getArgument(0);
            HeadBucketRequest.Builder builder = HeadBucketRequest.builder();
            consumer.accept(builder);
            return response;
        });

        String result = s3Service.checkIfBucketExist("existing-bucket");
        assertEquals("Bucket existing-bucket exists.", result);
    }

    @Test
    void checkIfBucketExist_shouldReturnNotExistMessage() {
        when(s3Client.headBucket(any(Consumer.class))).thenAnswer(invocation -> {
            throw S3Exception.builder()
                    .message("Not Found")
                    .statusCode(404)
                    .build();
        });

        String result = s3Service.checkIfBucketExist("missing-bucket");
        assertEquals("Bucket missing-bucket doesn't exist.", result);
    }

    @Test
    void getAllBuckets_shouldReturnList() {
        ListBucketsResponse response = ListBucketsResponse.builder()
                .buckets(
                        Bucket.builder().name("bucket1").build(),
                        Bucket.builder().name("bucket2").build()
                )
                .build();

        when(s3Client.listBuckets()).thenReturn(response);

        List<String> result = s3Service.getAllBuckets();
        assertEquals(List.of("bucket1", "bucket2"), result);
    }

    @Test
    void uploadFile_shouldReturnTrueOnSuccess() throws IOException {
        Path filePath = Files.createTempFile(tempDir, "test", ".txt");
        Files.writeString(filePath, "test data");

        SdkHttpResponse sdkHttpResponse = mock(SdkHttpResponse.class);
        when(sdkHttpResponse.isSuccessful()).thenReturn(true);

        PutObjectResponse putObjectResponse = mock(PutObjectResponse.class);
        when(putObjectResponse.sdkHttpResponse()).thenReturn(sdkHttpResponse);

        when(s3Client.putObject(any(PutObjectRequest.class), eq(filePath))).thenReturn(putObjectResponse);

        boolean result = s3Service.uploadFile("bucket", "key", filePath);
        assertTrue(result);
    }

    @Test
    void downloadFile_shouldWriteFile() throws IOException {
        byte[] content = "Test file content".getBytes();

        ResponseBytes<GetObjectResponse> responseBytes = ResponseBytes.fromByteArray(
                GetObjectResponse.builder().build(),
                content
        );

        when(s3Client.getObjectAsBytes(any(GetObjectRequest.class))).thenReturn(responseBytes);

        String testKey = "test.txt";
        s3Service.downloadFile("bucket", testKey);

        Path expectedPath = tempDir.resolve("others").resolve(testKey);
        assertTrue(Files.exists(expectedPath), "Expected downloaded file to exist at: " + expectedPath);
        assertEquals("Test file content", Files.readString(expectedPath));
    }
}
