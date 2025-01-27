package com.ga5000.api.ecommerce.repository.category;

import com.ga5000.api.ecommerce.domain.product.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Category findByNameIgnoreCase(String name);
}
