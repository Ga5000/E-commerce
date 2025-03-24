package com.ga5000.api.ecommerce.service.product;

import com.ga5000.api.ecommerce.domain.repository.product.ProductRepository;
import com.ga5000.api.ecommerce.service.category.CategoryService;
import com.ga5000.api.ecommerce.service.image.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ImageService imageService;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, ImageService imageService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.imageService = imageService;
        this.categoryService = categoryService;
    }
}
