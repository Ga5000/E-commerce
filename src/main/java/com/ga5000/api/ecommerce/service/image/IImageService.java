package com.ga5000.api.ecommerce.service.image;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.image.ImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IImageService {
    void addImageToProduct(MultipartFile file, Product product);
    void deleteImage(UUID imageId);

    List<ImageResponseDto> getAllProductImages(Product product);
}
