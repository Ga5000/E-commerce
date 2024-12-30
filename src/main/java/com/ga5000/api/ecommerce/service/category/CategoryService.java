package com.ga5000.api.ecommerce.service.category;

import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.dto.category.CategoryResponseDto;
import com.ga5000.api.ecommerce.repository.category.CategoryRepository;
import com.ga5000.api.ecommerce.utils.exceptions.Message;
import com.ga5000.api.ecommerce.utils.mapper.Mapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void createCategory(String categoryName) {
        if (categoryRepository.findByCategoryNameIgnoreCase(categoryName).isPresent()) {
            throw new EntityExistsException(Message.CategoryMessage.CATEGORY_EXISTS.name());
        }
        categoryRepository.save(new Category(categoryName));
    }

    @Override
    public void updateCategory(UUID categoryId, String categoryName) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(Message.CategoryMessage.CATEGORY_NOT_FOUND.name()));
        category.setCategoryName(categoryName);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(Message.CategoryMessage.CATEGORY_NOT_FOUND.name()));
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryResponseDto> getCategories() {
        return categoryRepository.findAll(Sort.by("categoryName").ascending())
                .stream()
                .map(Mapper::toCategoryResponseDto)
                .toList();
    }
}
