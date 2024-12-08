package com.ga5000.api.ecommerce.adapters.outbounds.repository;

import com.ga5000.api.ecommerce.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
