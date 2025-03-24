package com.ga5000.api.ecommerce.service.product;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IProductService {
    void createProduct();
    void updateProductName(UUID id);
    void updateProductPrice(UUID id);
    void updateProductImage(UUID id, MultipartFile file);
    void deleteProduct(UUID id);
}
