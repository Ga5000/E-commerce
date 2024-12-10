package com.ga5000.api.ecommerce.application.usecases.product.contract;

import com.ga5000.api.ecommerce.application.usecases.product.dto.ProductFilterDTO;
import com.ga5000.api.ecommerce.application.usecases.product.dto.ProductRequestDTO;
import com.ga5000.api.ecommerce.application.usecases.product.dto.ProductResponseDTO;
import com.ga5000.api.ecommerce.application.usecases.pagination.dto.PaginatedResponseDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public interface ProductManagementUseCase {
    void createProduct(ProductRequestDTO productRequestDTO) throws EntityExistsException; // create a product (only for admins)
    void updateProduct(UUID productId, ProductRequestDTO productRequestDTO) throws EntityNotFoundException, EntityExistsException; // update product (only for admins)
    void deleteProduct(UUID productId) throws EntityNotFoundException; // delete product (only for admins)
    PaginatedResponseDTO<ProductResponseDTO> getProductsByFilter(ProductFilterDTO filter,
                                                                 int pageNumber, int pageSize, String sortBy, String direction); // for all users

}
