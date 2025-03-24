package com.ga5000.api.ecommerce.service.image;

import com.ga5000.api.ecommerce.domain.model.image.Image;
import com.ga5000.api.ecommerce.domain.model.product.Product;
import com.ga5000.api.ecommerce.domain.repository.image.ImageRepository;
import com.ga5000.api.ecommerce.service.minio.MinioS3Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.ga5000.api.ecommerce.utils.RepositoryUtils.findByIdOrThrow;

@Service
public class ImageService implements IImageService {
    private final ImageRepository imageRepository;
    private final MinioS3Service minioS3Service;
    private final String IMAGE_NOT_FOUND_MESSAGE = "Image not found";

    public ImageService(ImageRepository imageRepository, MinioS3Service minioS3Service) {
        this.imageRepository = imageRepository;
        this.minioS3Service = minioS3Service;
    }

    @Override
    public void uploadImage(MultipartFile file, Product product) {
        String objectId = minioS3Service.uploadImage(file);
       saveImage(new Image(objectId, product));
    }

    @Override
    public void deleteImage(UUID id) {
        var image = findByIdOrThrow(id, imageRepository, () -> new EntityNotFoundException(IMAGE_NOT_FOUND_MESSAGE));
        minioS3Service.deleteImage(image.getObjectId());
        imageRepository.delete(image);
    }

    private void saveImage(Image image) {
        imageRepository.save(image);
    }
}
