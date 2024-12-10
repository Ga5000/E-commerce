package com.ga5000.api.ecommerce.adapters.outbounds.repository.category;

import com.ga5000.api.ecommerce.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByCategoryNameIgnoreCase(String categoryName);
}
