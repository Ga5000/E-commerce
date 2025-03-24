package com.ga5000.api.ecommerce.service.category;

import com.ga5000.api.ecommerce.dto.category.CategoryRequest;
import com.ga5000.api.ecommerce.dto.category.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    void createCategory(CategoryRequest request);
    void updateCategory(UUID id, CategoryRequest request);
    void deleteCategory(UUID id);
    List<CategoryResponse> getCategories();
    void addCategoryToProduct(UUID id, UUID productId);
}
