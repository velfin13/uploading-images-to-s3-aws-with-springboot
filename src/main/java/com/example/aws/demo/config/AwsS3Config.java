package com.example.aws.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.aws.demo.utils.AwsProperties;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsS3Config {

        @Autowired
        private AwsProperties awsProperties;

        @Bean
        public S3Client s3Client() {
                return S3Client.builder()
                                .region(Region.of(awsProperties.getRegion()))
                                .credentialsProvider(StaticCredentialsProvider.create(
                                                AwsBasicCredentials.create(
                                                                awsProperties.getAccessKeyId(),
                                                                awsProperties.getSecretAccessKey())))
                                .build();
        }
}
