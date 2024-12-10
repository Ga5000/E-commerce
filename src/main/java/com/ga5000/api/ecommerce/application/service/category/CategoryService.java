package com.ga5000.api.ecommerce.application.service.category;

import com.ga5000.api.ecommerce.adapters.outbounds.repository.category.CategoryRepository;
import com.ga5000.api.ecommerce.application.usecases.category.contract.CategoryManagementUseCase;
import com.ga5000.api.ecommerce.application.usecases.category.dto.CategoryRequestDto;
import com.ga5000.api.ecommerce.application.usecases.category.dto.CategoryResponseDTO;
import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.utils.mapper.Mapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public class CategoryService implements CategoryManagementUseCase {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void createCategory(CategoryRequestDto categoryRequestDto) throws EntityExistsException {
        categoryRepository.findByCategoryNameIgnoreCase(categoryRequestDto.categoryName())
                .ifPresentOrElse(category ->
                        {throw new EntityExistsException("Uma categoria com este nome já existe");},
                        () -> {
                            Category category = new Category();
                            BeanUtils.copyProperties(categoryRequestDto,category);
                            categoryRepository.save(category);
                        });
    }

    @Override
    public void deleteCategory(UUID categoryId) throws EntityNotFoundException {
        categoryRepository.findById(categoryId)
                .ifPresentOrElse(categoryRepository::delete,
                        () -> {throw new EntityNotFoundException("Categoria não encontrada");});
    }

    @Override
    public void updateCategory(UUID categoryId, String name) throws EntityExistsException, EntityNotFoundException {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        if(!existingCategory.getCategoryName().equalsIgnoreCase(name)){
            categoryRepository.findByCategoryNameIgnoreCase(name)
                    .ifPresent(category -> {
                        throw new EntityExistsException("Uma categoria com este nome já existe");
                    });
        }

        existingCategory.setCategoryName(name);
        categoryRepository.save(existingCategory);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryName"))
                .stream()
                .map(Mapper::categoryToCategoryResponseDTO)
                .toList();
    }

}
