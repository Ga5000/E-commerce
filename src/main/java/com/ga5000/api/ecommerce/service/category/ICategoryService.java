package com.ga5000.api.ecommerce.service.category;

import com.ga5000.api.ecommerce.dto.CategoryResponseDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    void createCategory(String categoryName) throws EntityExistsException;
    void updateCategory(UUID categoryId, String categoryName) throws EntityNotFoundException;
    void deleteCategory(UUID categoryId) throws EntityNotFoundException;

    List<CategoryResponseDto> getCategories();
}
