package com.ga5000.api.product.service.product;

import com.ga5000.api.product.dto.request.product.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest request, List<MultipartFile> files); // files -> images
}
