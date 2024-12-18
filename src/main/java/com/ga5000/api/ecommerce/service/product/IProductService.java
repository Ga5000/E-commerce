package com.ga5000.api.ecommerce.service.product;

import com.ga5000.api.ecommerce.dto.pagination.PaginatedResponseDto;
import com.ga5000.api.ecommerce.dto.product.ProductRequestDto;
import com.ga5000.api.ecommerce.dto.product.ProductResponseDto;
import com.ga5000.api.ecommerce.dto.product.ProductSearchFilterDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public interface IProductService {
    void addProduct(ProductRequestDto productRequestDto) throws EntityExistsException;
    void updateProduct(UUID productId, ProductRequestDto productRequestDto) throws EntityNotFoundException, EntityExistsException;
    void deleteProduct(UUID productId) throws EntityNotFoundException;

    PaginatedResponseDto<ProductResponseDto> getProducts(ProductSearchFilterDto filter, int pageNumber, int pageSize);
}
