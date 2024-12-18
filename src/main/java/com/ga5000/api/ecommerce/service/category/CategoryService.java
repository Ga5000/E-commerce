package com.ga5000.api.ecommerce.service.category;

import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.dto.category.CategoryRequestDto;
import com.ga5000.api.ecommerce.dto.category.CategoryResponseDto;
import com.ga5000.api.ecommerce.repository.category.CategoryRepository;
import com.ga5000.api.ecommerce.utils.Mapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private static final String CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE = "Categoria não encontrada";
    private static final String CATEGORY_EXISTS_EXCEPTION_MESSAGE = "Essa categoria já existe";

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void addCategory(CategoryRequestDto categoryRequestDto) throws EntityExistsException {
        categoryRepository.findByCategoryNameIgnoreCase(categoryRequestDto.categoryName())
                .ifPresentOrElse((existingCategory -> {
                    throw new EntityExistsException(CATEGORY_EXISTS_EXCEPTION_MESSAGE);
                }), () -> {
                    var newCategory = new Category();
                    BeanUtils.copyProperties(categoryRequestDto, newCategory);
                    categoryRepository.save(newCategory);
                });
    }

    @Override
    public void updateCategory(UUID categoryId, CategoryRequestDto categoryRequestDto) throws EntityNotFoundException, EntityExistsException {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE));

        if(!existingCategory.getCategoryName().equalsIgnoreCase(categoryRequestDto.categoryName())) {
            throw new EntityExistsException(CATEGORY_EXISTS_EXCEPTION_MESSAGE);
        }
        BeanUtils.copyProperties(categoryRequestDto, existingCategory);
        categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(UUID categoryId) throws EntityNotFoundException {
        categoryRepository.findById(categoryId)
                .ifPresentOrElse(categoryRepository::delete, () -> {throw new EntityNotFoundException(CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE);});
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll(Sort.by("categoryName")).stream().map(Mapper::toCategoryResponseDto).toList();
    }
}
