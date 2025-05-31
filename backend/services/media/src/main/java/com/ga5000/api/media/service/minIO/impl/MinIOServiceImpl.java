package com.ga5000.api.media.service.minIO.impl;


import com.ga5000.api.media.service.minIO.MinIOService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MinIOServiceImpl implements MinIOService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String endpoint;


    @Override
    public String uploadFile(String filename, String contentType, byte[] data) {
        InputStream inputStream = new ByteArrayInputStream(data);
        try{
            minioClient.putObject(buildPutObjectArgs(filename, contentType, inputStream, data));
        }catch (Exception e){
           throw new RuntimeException("Failed to upload file");
        }
        return buildFileUrl(filename);
    }

    @Override
    public void deleteFile(String filename) {
        try{
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filename)
                    .build()
            );
        }catch (Exception e){
            throw new RuntimeException("Failed to delete file");
        }
    }

    private PutObjectArgs buildPutObjectArgs(String filename, String contentType, InputStream inputStream, byte[] data){
            return PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filename)
                    .stream(inputStream, data.length, -1)
                    .contentType(contentType)
                    .build();
    }

    private String buildFileUrl(String filename) {
        return String.format("%s/%s/%s", endpoint, bucketName, filename);
    }
}
