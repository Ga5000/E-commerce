package com.ga5000.api.ecommerce.service.category;

import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.category.CategoryRequest;
import com.ga5000.api.ecommerce.dto.category.CategoryResponse;
import com.ga5000.api.ecommerce.exception.category.CategoryAlreadyExistsException;
import com.ga5000.api.ecommerce.exception.category.CategoryNotFoundException;
import com.ga5000.api.ecommerce.exception.category.NoValueChangeException;
import com.ga5000.api.ecommerce.repository.category.CategoryRepository;
import com.ga5000.api.ecommerce.utils.DtoMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.ga5000.api.ecommerce.utils.RepositoryUtils.areFieldsEqual;
import static com.ga5000.api.ecommerce.utils.RepositoryUtils.findByIdOrThrow;

@Service
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;
    private final DtoMapper dtoMapper;

    public CategoryService(CategoryRepository categoryRepository, DtoMapper dtoMapper) {
        this.categoryRepository = categoryRepository;
        this.dtoMapper = dtoMapper;
    }

    @Transactional
    @Override
    public void createCategory(CategoryRequest categoryRequest) {
        validateCategory(categoryRequest.name());
        saveCategory(new Category(categoryRequest.name()));
    }

    @Transactional
    @Override
    public void createCategories(List<CategoryRequest> categoryRequests) {
        for (var category : categoryRequests){
            validateCategory(category.name());
        }
        List<Category> categories = categoryRequests.stream()
                .map(category -> new Category(category.name())).toList();

        categoryRepository.saveAll(categories);
    }

    @Transactional
    @Override
    public void updateCategory(UUID categoryId, CategoryRequest categoryRequest) throws NoValueChangeException{
        String newName = categoryRequest.name();
        Category existingCategory = findByIdOrThrow(categoryId, categoryRepository,
                CategoryNotFoundException::new);
        if(areFieldsEqual(existingCategory.getName(), newName)){
            throw new NoValueChangeException();
        }
        validateCategory(newName);
        existingCategory.setName(newName);
        saveCategory(existingCategory);
    }

    @Transactional
    @Override
    public void deleteCategory(UUID categoryId){
        Category existingCategory = findByIdOrThrow(categoryId, categoryRepository,
                CategoryNotFoundException::new);
        categoryRepository.delete(existingCategory);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll(Sort.by( Sort.Direction.ASC, "name"))
                .stream().map(dtoMapper::toCategoryResponse).toList();
    }

    @Override
    public List<CategoryResponse> getProductCategories(Product product) {
        List<Category> categories = categoryRepository.findCategoriesByProductId(product.getProductId());

        return categories.stream().map(dtoMapper::toCategoryResponse).toList();
    }


    private void validateCategory(String name) throws CategoryAlreadyExistsException {
        Category category = categoryRepository.findByName(name);
        if(category != null){
            throw new CategoryAlreadyExistsException();
        }

    }

    private void saveCategory(Category category){
        categoryRepository.save(category);
    }
}
