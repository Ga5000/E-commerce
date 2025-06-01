package com.ga5000.api.product.service.category;


import com.ga5000.api.product.domain.model.product.Product;
import com.ga5000.api.product.dto.request.category.CategoryRequest;

import java.util.UUID;

public interface CategoryService {
    void createCategory(CategoryRequest request);
    void updateCategoryName(UUID categoryId, CategoryRequest request);
    void deleteCategory(UUID categoryId);

    void assignCategoryToProduct(UUID categoryId, Product product);
    void removeCategoryFromProduct(UUID categoryId, Product product);
}
