package com.ga5000.api.ecommerce.application.usecases.category.contract;

import com.ga5000.api.ecommerce.application.usecases.category.dto.CategoryRequestDto;
import com.ga5000.api.ecommerce.application.usecases.category.dto.CategoryResponseDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface CategoryManagementUseCase {
    void createCategory(CategoryRequestDto categoryRequestDto) throws EntityExistsException;
    void deleteCategory(UUID categoryId) throws EntityNotFoundException;
    void updateCategory(UUID categoryId, String name) throws EntityExistsException, EntityNotFoundException;
    List<CategoryResponseDTO> getAllCategories();
}
