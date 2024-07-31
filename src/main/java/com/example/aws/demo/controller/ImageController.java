package com.example.aws.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.aws.demo.utils.AwsFolders;
import com.example.aws.demo.utils.AwsProperties;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private AwsProperties awsProperties;

    private AwsFolders awsFolders;

    @Autowired
    private S3Client s3Client;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String key = awsFolders.getUnidadEducativaMilenioJunin_personProfile() + UUID.randomUUID().toString() + file.getOriginalFilename();

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(awsProperties.getBucketName())
                    .contentType("image/jpeg")
                    .key(key)
                    .build();
            s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
            return ResponseEntity.ok(awsProperties.getBaseUrl() + key);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la imagen: " + e.getMessage());
        }
    }
}
