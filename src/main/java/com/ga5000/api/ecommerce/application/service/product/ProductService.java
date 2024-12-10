package com.ga5000.api.ecommerce.application.service.product;

import com.ga5000.api.ecommerce.adapters.outbounds.repository.product.ProductRepository;
import com.ga5000.api.ecommerce.adapters.outbounds.repository.product.ProductSpecifications;
import com.ga5000.api.ecommerce.application.usecases.pagination.dto.PaginatedResponseDTO;
import com.ga5000.api.ecommerce.application.usecases.product.contract.ProductManagementUseCase;
import com.ga5000.api.ecommerce.application.usecases.product.dto.ProductFilterDTO;
import com.ga5000.api.ecommerce.application.usecases.product.dto.ProductRequestDTO;
import com.ga5000.api.ecommerce.application.usecases.product.dto.ProductResponseDTO;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.utils.mapper.Mapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public class ProductService implements ProductManagementUseCase {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void createProduct(ProductRequestDTO productRequestDTO) {
        productRepository.findByProductNameIgnoreCase(productRequestDTO.productName())
                .ifPresentOrElse(product -> {
                    throw new EntityExistsException("Um produto com este nome já existe");
                }, () -> {
                    Product product = new Product();
                    BeanUtils.copyProperties(productRequestDTO, product);
                    productRepository.save(product);
                });
    }

    @Override
    public void updateProduct(UUID productId, ProductRequestDTO productRequestDTO) throws EntityNotFoundException, EntityExistsException {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        if(!existingProduct.getProductName().equalsIgnoreCase(productRequestDTO.productName())) {
            productRepository.findByProductNameIgnoreCase(productRequestDTO.productName())
                    .ifPresent(product -> {
                        throw new EntityExistsException("Um produto com este nome já existe");
                    });
        }
        BeanUtils.copyProperties(productRequestDTO, existingProduct);
        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID productId) throws EntityNotFoundException {
        productRepository.findById(productId)
                .ifPresentOrElse(productRepository::delete, () -> {
                    throw new EntityNotFoundException("Produto não encontrado");
                });
    }

    @Override
    public PaginatedResponseDTO<ProductResponseDTO> getProductsByFilter(ProductFilterDTO filter, int pageNumber,
                                                                        int pageSize, String sortBy, String direction) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortBy));
        Page<ProductResponseDTO> productPage = productRepository.findAll(ProductSpecifications.withFilters(filter), pageable)
                .map(Mapper::productToProductResponseDTO);

        return new PaginatedResponseDTO<>(
                productPage.getContent(),
                productPage.getTotalPages(),
                productPage.getTotalElements(),
                productPage.getNumber()
        );
    }

}
