package com.ga5000.api.ecommerce.service.image;

import com.ga5000.api.ecommerce.domain.model.product.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IImageService {
    void uploadImage(MultipartFile file, Product product);
    void deleteImage(UUID id);
}
