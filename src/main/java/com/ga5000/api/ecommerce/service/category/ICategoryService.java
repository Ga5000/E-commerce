package com.ga5000.api.ecommerce.service.category;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.category.CategoryRequest;
import com.ga5000.api.ecommerce.dto.category.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    void createCategory(CategoryRequest categoryRequest);
    void createCategories(List<CategoryRequest> categoryRequests);
    void updateCategory(UUID categoryId, CategoryRequest categoryRequest);
    void deleteCategory(UUID categoryId);

    List<CategoryResponse> getAllCategories();
    List<CategoryResponse> getProductCategories(Product product);
}
