package com.ga5000.api.ecommerce.aws.s3bucket;

import com.ga5000.api.ecommerce.exception.InvalidFileException;
import com.ga5000.api.ecommerce.utils.exceptions.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;


public class Bucket {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    @Value("${aws.s3.bucketName}")
    private final String bucketName;

    public Bucket(String bucketName) {
        this.s3Client = S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
        this.s3Presigner = S3Presigner.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
        this.bucketName = bucketName;
    }

    public void uploadFile(MultipartFile multipartFile) throws S3Exception, IOException {
        validateFileType(multipartFile);
        String key = generateKey(multipartFile);
        checkIfFileExists(key);
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(multipartFile.getInputStream(), multipartFile.getSize()));
    }

    public URL getFileUrl(String key) throws S3Exception {
        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .getObjectRequest(builder -> builder.bucket(bucketName).key(key))
                .build();

        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(getObjectPresignRequest);
        return presignedGetObjectRequest.url();
    }

    public String generateKey(MultipartFile file) {
        return "uploads/images/" + file.getOriginalFilename();
    }

    private void validateFileType(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename == null || !(filename.endsWith(".png") || filename.endsWith(".jpg"))) {
            throw new InvalidFileException(Message.BucketMessage.INVALID_FILE_TYPE.name());
        }
    }

    private void checkIfFileExists(String key) {
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            s3Client.headObject(headObjectRequest);
            throw new InvalidFileException(Message.BucketMessage.FILE_WITH_SAME_NAME_ALREADY_EXISTS.name());
        } catch (NoSuchKeyException e) {
            // File does not exist, so proceed with the upload
        }
    }
}
