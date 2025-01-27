package com.ga5000.api.ecommerce.service.category;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.category.CategoryResponseDto;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    void addCategory(String name);
    void updateCategory(UUID categoryId, String name);
    void deleteCategory(UUID categoryId);

    List<CategoryResponseDto> getAllCategories();
    void addCategoryToProduct(UUID categoryId, Product product);
}
