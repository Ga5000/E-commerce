package com.ga5000.api.product.service.category.impl;

import com.ga5000.api.product.domain.model.category.Category;
import com.ga5000.api.product.domain.model.product.Product;
import com.ga5000.api.product.domain.repository.category.CategoryRepository;
import com.ga5000.api.product.dto.request.category.CategoryRequest;
import com.ga5000.api.product.service.category.CategoryService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepo;

    @Transactional
    @Override
    public void createCategory(CategoryRequest request) {
        String name = request.name();
        validateCategoryName(name);

        saveCategory(new Category(name));
    }

    @Override
    public void updateCategoryName(UUID categoryId, CategoryRequest request) {
        String newName = request.name();
        validateCategoryName(newName);

        var category = getCategoryById(categoryId);

        category.changeName(newName);
        saveCategory(category);
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        var category = getCategoryById(categoryId);
        if (category.hasProducts()) throw new RuntimeException("Cannot delete category with associated products.");

        categoryRepo.delete(category);
    }

    @Override
    public void assignCategoryToProduct(UUID categoryId, Product product) {
        var category = getCategoryById(categoryId);

        category.addProduct(product);
        product.addCategory(category);

        saveCategory(category);
    }

    @Override
    public void removeCategoryFromProduct(UUID categoryId, Product product) {
        var category = getCategoryById(categoryId);

        category.removeProduct(product);
        product.removeCategory(category);
    }

    private void validateCategoryName(String name) {
        if (categoryRepo.existsByNameIgnoreCase(name))
            throw new EntityExistsException("Category with name '" + name + "' already exists.");
    }

    private void saveCategory(Category category) {
        categoryRepo.save(category);
    }

    private Category getCategoryById(UUID categoryId) {
        return categoryRepo.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category was deleted or doesn't exist."));
    }
}
