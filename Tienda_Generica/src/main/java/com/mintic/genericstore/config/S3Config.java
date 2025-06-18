package com.mintic.genericstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Configuration
public class S3Config {

    @Value("${aws.access.key}")
    private String awsAccessKey;

    @Value("${aws.secret.key}")
    private String awsSecretKey;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.overrideUrl}")
    private String overrideUrl;

    /**
     *  Sync S3 Client
     */
    @Bean
    public S3Client getS3client(){
        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);

        return S3Client.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(overrideUrl))
                .credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .build();
    }

    /**
     *  Async S3 Client
     */
    @Bean
    public S3AsyncClient getS3AsyncClient(){
        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);
        return S3AsyncClient.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(overrideUrl))
                .credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .forcePathStyle(true)
                .build();
    }

    /**
     *  URl's S3 Signature
     */
    @Bean
    public S3Presigner getS3Presigner(){
        AwsBasicCredentials basicCredentials = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);

        return S3Presigner.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(basicCredentials))
                .build();
    }
}
