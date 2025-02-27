package com.ga5000.api.ecommerce.repository.product;

import com.ga5000.api.ecommerce.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
    Product findByNameIgnoreCase(String name);

    @Query("SELECT p FROM Product p ORDER BY FUNCTION('RAND')")
    Page<Product> findRandomProducts(Pageable pageable);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO product_categories (product_id, category_id) VALUES (:productId, :categoryId)", nativeQuery = true)
    void associateProductWithCategory(UUID productId, UUID categoryId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_categories " +
            "WHERE product_id = :productId " +
            "AND category_id NOT IN (:categoryIds)",
            nativeQuery = true)
    void removeUnlistedCategories(UUID  productId, List<UUID> categoryIds);

    @Modifying
    @Transactional
    @Query(value = "INSERT IGNORE INTO product_categories (product_id, category_id) " +
            "SELECT :productId, c.category_id FROM categories c " +
            "WHERE c.category_id IN (:categoryIds)",
            nativeQuery = true)
    void addNewCategories( UUID productId,  List<UUID> categoryIds);


}
