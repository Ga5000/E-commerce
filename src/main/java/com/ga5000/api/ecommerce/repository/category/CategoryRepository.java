package com.ga5000.api.ecommerce.repository.category;

import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Category findByName(String name);

    @Query(value = "SELECT c.* FROM categories c " +
            "JOIN product_categories pc ON c.category_id = pc.category_id " +
            "WHERE pc.product_id = :productId",
            nativeQuery = true)
    List<Category> findCategoriesByProductId(UUID productId);
}
