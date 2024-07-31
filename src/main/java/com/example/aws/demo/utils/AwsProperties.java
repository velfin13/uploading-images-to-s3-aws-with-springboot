package com.example.aws.demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@PropertySource("classpath:application.properties")
public class AwsProperties {
    @Value("${aws.s3.accessKeyId}")
	private String accessKeyId;

    @Value("${aws.s3.secretAccessKey}")
	private String secretAccessKey;

    @Value("${aws.s3.region}")
	private String region;

    @Value("${aws.s3.bucket-name}")
	private String bucketName;

    @Value("${aws.s3.base-url}")
	private String baseUrl;
}
