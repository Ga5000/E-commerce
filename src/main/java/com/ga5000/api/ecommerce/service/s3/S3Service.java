package com.ga5000.api.ecommerce.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ga5000.api.ecommerce.exception.AwsServiceException;
import com.ga5000.api.ecommerce.exception.InvalidImageTypeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static com.ga5000.api.ecommerce.utils.ExceptionMessage.INVALID_IMAGE_TYPE;

@Service
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String uploadImage(MultipartFile file) throws IOException, AwsServiceException {
        if (!isValidImageType(file)) {
            throw new InvalidImageTypeException(INVALID_IMAGE_TYPE);
        }

        String fileName = generateUniqueFileName(Objects.requireNonNull(file.getOriginalFilename()));

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);

        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    private boolean isValidImageType(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (contentType.equals("image/jpeg") || contentType.equals("image/png"));
    }

    private String generateUniqueFileName(String originalFilename) {
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        return UUID.randomUUID() + extension;
    }
}
