package com.ga5000.api.ecommerce.domain.repository.category;

import com.ga5000.api.ecommerce.domain.model.product.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    boolean existsByNameIgnoreCase(String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO product_categories_tb (product_id, category_id) VALUES (:productId, :categoryId)", nativeQuery = true)
    void associateCategoryWithProduct(@Param("productId") UUID productId, @Param("categoryId") UUID categoryId);
}
