package com.ga5000.api.ecommerce.service.category;

import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.dto.category.CategoryRequestDto;
import com.ga5000.api.ecommerce.dto.category.CategoryResponseDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    void addCategory(CategoryRequestDto categoryRequestDto) throws EntityExistsException;
    void updateCategory(UUID categoryId, CategoryRequestDto categoryRequestDto) throws EntityNotFoundException, EntityExistsException;
    void deleteCategory(UUID categoryId) throws EntityNotFoundException;

    List<CategoryResponseDto> getAllCategories();
}
