package com.ga5000.api.ecommerce.service.product;


import com.ga5000.api.ecommerce.dto.ProductRequestDto;
import com.ga5000.api.ecommerce.dto.ProductResponseDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import java.util.UUID;

public interface IProductService {
    void createProduct(ProductRequestDto productRequestDto) throws EntityExistsException;
    void updateProduct(UUID productId, ProductRequestDto productRequestDto) throws EntityExistsException, EntityNotFoundException;
    void deleteProduct(UUID productId) throws EntityNotFoundException;

    Page<ProductResponseDto> getProducts();


}
