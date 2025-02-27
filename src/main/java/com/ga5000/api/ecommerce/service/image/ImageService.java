package com.ga5000.api.ecommerce.service.image;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.domain.image.Image;
import com.ga5000.api.ecommerce.dto.image.ImageRequest;
import com.ga5000.api.ecommerce.dto.image.ImageResponse;
import com.ga5000.api.ecommerce.repository.image.ImageRepository;
import com.ga5000.api.ecommerce.service.minioS3.MinioS3Service;
import com.ga5000.api.ecommerce.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImageService implements IImageService {
    private final MinioS3Service minioS3Service;
    private final ImageRepository imageRepository;
    private final DtoMapper dtoMapper;


    public ImageService(MinioS3Service minioS3Service, ImageRepository imageRepository, DtoMapper dtoMapper) {
        this.minioS3Service = minioS3Service;
        this.imageRepository = imageRepository;
        this.dtoMapper = dtoMapper;
    }

    @Transactional
    @Override
    public void addImageToProduct(ImageRequest imageRequest) {
        var objectId = minioS3Service.uploadImage(imageRequest.file());
        Image newImage = new Image(objectId, imageRequest.product());
        imageRepository.save(newImage);
    }

    @Transactional
    @Override
    public void removeImage(Image image) {
        minioS3Service.deleteImage(image.getObjectId());
        imageRepository.delete(image);
    }

    @Override
    public List<ImageResponse> getProductImages(Product product) {
        return product.getImages()
                .stream()
                .map(dtoMapper::toImageResponse).toList();
    }
}
