package com.ga5000.api.ecommerce.service.category;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.domain.product.category.Category;
import com.ga5000.api.ecommerce.dto.category.CategoryResponseDto;
import com.ga5000.api.ecommerce.repository.category.CategoryRepository;
import com.ga5000.api.ecommerce.utils.Mapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.ga5000.api.ecommerce.utils.ExceptionMessage.CATEGORY_NOT_FOUND;
import static com.ga5000.api.ecommerce.utils.RepositoryUtil.getById;
import static com.ga5000.api.ecommerce.utils.ValidationUtil.checkEntityAvailability;
import static com.ga5000.api.ecommerce.utils.ValidationUtil.isSameValue;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void addCategory(String name) {
        checkEntityAvailability(name, categoryRepository, categoryRepository::findByNameIgnoreCase, CATEGORY_NOT_FOUND);
        var newCategory = new Category(name);
        saveCategory(newCategory);
    }

    @Override
    @Transactional
    public void updateCategory(UUID categoryId, String name) {
        Category category = getById(categoryId, categoryRepository, CATEGORY_NOT_FOUND);
        if(!isSameValue(name, category.getName())) {
            checkEntityAvailability(name, categoryRepository, categoryRepository::findByNameIgnoreCase,
                                                                                    CATEGORY_NOT_FOUND);
        }

        category.setName(name);
        saveCategory(category);
    }

    @Override
    @Transactional
    public void deleteCategory(UUID categoryId) {
        Category category = getById(categoryId, categoryRepository, CATEGORY_NOT_FOUND);
        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll(Sort.by(Sort.Order.asc("name")))
                                 .stream()
                                 .map(Mapper::toCategoryResponseDto)
                                 .toList();
    }

    @Override
    public void addCategoryToProduct(UUID categoryId, Product product) {
        Category category = getById(categoryId, categoryRepository, CATEGORY_NOT_FOUND);
        if (category.getProduct() != null) {
            if (!category.getProduct().equals(product)) {
                category.setProduct(product);
            }
        } else {
            category.setProduct(product);
        }
        saveCategory(category);
    }

    private void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
