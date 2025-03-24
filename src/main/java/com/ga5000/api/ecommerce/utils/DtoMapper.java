package com.ga5000.api.ecommerce.utils;

import com.ga5000.api.ecommerce.domain.model.product.category.Category;
import com.ga5000.api.ecommerce.dto.category.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    public CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName()
        );
    }
}
