package com.ga5000.api.product.service.category.impl;

import com.ga5000.api.product.domain.model.product.Product;
import com.ga5000.api.product.domain.repository.category.CategoryRepository;
import com.ga5000.api.product.dto.CategoryRequest;
import com.ga5000.api.product.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepo;

    @Override
    public void createCategory(CategoryRequest request) {

    }

    @Override
    public void updateCategoryName(UUID categoryId, CategoryRequest request) {

    }

    @Override
    public void deleteCategory(UUID categoryId) {

    }

    @Override
    public void assignCategoryToProduct(UUID categoryId, Product product) {

    }

    @Override
    public void removeCategoryFromProduct(UUID categoryId, Product product) {

    }
}
