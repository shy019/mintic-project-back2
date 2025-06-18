package com.mintic.genericstore.controller.impl;

import com.mintic.genericstore.service.S3Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

class S3ControllerImplTest {

    @InjectMocks
    private S3ControllerImpl s3Controller;

    @Mock
    private S3Service s3Service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBucket_shouldReturnSuccess() {
        when(s3Service.createBucket("test-bucket")).thenReturn("Bucket created at: /test-bucket");

        ResponseEntity<String> response = s3Controller.createBucket("test-bucket");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Bucket created at: /test-bucket", response.getBody());
    }

    @Test
    void uploadFile_shouldReturnSuccess() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "hello".getBytes());

        when(s3Service.uploadFile("my-bucket", "file-key", file)).thenReturn(true);

        ResponseEntity<String> response = s3Controller.uploadFile("my-bucket", "file-key", file);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("File uploaded successfully", response.getBody());
    }

    @Test
    void listBuckets_shouldReturnList() {
        when(s3Service.getAllBuckets()).thenReturn(List.of("bucket1", "bucket2"));

        ResponseEntity<List<String>> response = s3Controller.listBuckets();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void generatePresignedDownloadUrl_shouldReturnUrl() {
        when(s3Service.generatePresignedDownloadUrl("bucket", "file", Duration.ofMinutes(5)))
                .thenReturn("https://aws.amazon.com/s3/presigned");

        ResponseEntity<String> response = s3Controller.generatePresignedDownloadUrl("bucket", "file", 5L);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("https://"));
    }

    @Test
    void downloadFile_whenSuccess_returnsOk() throws IOException {
        String bucketName = "test-bucket";
        String key = "file.txt";

        doNothing().when(s3Service).downloadFile(bucketName, key);

        ResponseEntity<String> response = s3Controller.downloadFile(bucketName, key);

        assertEquals(OK, response.getStatusCode());
        assertEquals("File downloaded successfully", response.getBody());
        verify(s3Service, times(1)).downloadFile(bucketName, key);
    }

    @Test
    void downloadFile_whenIOException_throwsException() throws IOException {
        String bucketName = "test-bucket";
        String key = "file.txt";

        doThrow(new IOException("Download error")).when(s3Service).downloadFile(bucketName, key);

        IOException thrown = assertThrows(IOException.class, () -> {
            s3Controller.downloadFile(bucketName, key);
        });

        assertEquals("Download error", thrown.getMessage());
        verify(s3Service, times(1)).downloadFile(bucketName, key);
    }

    @Test
    void generatePresignedUploadUrl_shouldReturnUrlSuccessfully() {
        String bucketName = "test-bucket";
        String key = "file.txt";
        Long time = 15L;
        String expectedUrl = "https://presigned-url.com/upload";

        when(s3Service.generatePresignedUploadUrl(bucketName, key, Duration.ofMinutes(time)))
                .thenReturn(expectedUrl);

        ResponseEntity<String> response = s3Controller.generatePresignedUploadUrl(bucketName, key, time);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedUrl, response.getBody());

        verify(s3Service, times(1)).generatePresignedUploadUrl(bucketName, key, Duration.ofMinutes(time));
    }
}
