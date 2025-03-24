package com.ga5000.api.ecommerce.service.category;

import com.ga5000.api.ecommerce.domain.model.product.category.Category;
import com.ga5000.api.ecommerce.domain.repository.category.CategoryRepository;
import com.ga5000.api.ecommerce.dto.category.CategoryRequest;
import com.ga5000.api.ecommerce.dto.category.CategoryResponse;
import com.ga5000.api.ecommerce.exceptions.entity.EntityAlreadyExistsException;
import com.ga5000.api.ecommerce.utils.DtoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.ga5000.api.ecommerce.utils.RepositoryUtils.existsByIdOrThrow;
import static com.ga5000.api.ecommerce.utils.RepositoryUtils.findByIdOrThrow;

@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final DtoMapper dtoMapper;
    private final String CATEGORY_NOT_FOUND_MESSAGE = "Category not found";

    public CategoryService(CategoryRepository categoryRepository, DtoMapper dtoMapper) {
        this.categoryRepository = categoryRepository;
        this.dtoMapper = dtoMapper;
    }

    @Transactional
    @Override
    public void createCategory(CategoryRequest request) {
        validateCategoryAvailability(request.name());
        saveCategory(new Category(request.name()));
    }

    @Transactional
    @Override
    public void updateCategory(UUID id, CategoryRequest request) {
        var existingCategory = findByIdOrThrow(id, categoryRepository,
                                                () -> new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE));

        validateCategoryAvailability(request.name());
        existingCategory.setName(request.name());
        saveCategory(existingCategory);
    }

    @Transactional
    @Override
    public void deleteCategory(UUID id) {
        existsByIdOrThrow(id, categoryRepository, () -> new EntityNotFoundException(CATEGORY_NOT_FOUND_MESSAGE));
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll(Sort.by("name").ascending())
                .stream().map(dtoMapper::toCategoryResponse).toList();
    }

    @Transactional
    @Override
    public void addCategoryToProduct(UUID id, UUID productId) {
        categoryRepository.associateCategoryWithProduct(productId, id);
    }

    private void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    private void  validateCategoryAvailability(String name) throws EntityAlreadyExistsException{
        boolean exists = categoryRepository.existsByNameIgnoreCase(name);
        if (exists) {
            String CATEGORY_ALREADY_EXISTS_MESSAGE = "Category with %s %s already exists";
            throw new EntityAlreadyExistsException(String.format(CATEGORY_ALREADY_EXISTS_MESSAGE, "name:", name));
        }
    }
}
