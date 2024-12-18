package com.ga5000.api.ecommerce.service.product;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.pagination.PaginatedResponseDto;
import com.ga5000.api.ecommerce.repository.product.ProductRepository;
import com.ga5000.api.ecommerce.repository.product.ProductSpecification;
import com.ga5000.api.ecommerce.dto.product.ProductRequestDto;
import com.ga5000.api.ecommerce.dto.product.ProductResponseDto;
import com.ga5000.api.ecommerce.dto.product.ProductSearchFilterDto;
import com.ga5000.api.ecommerce.utils.Mapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private static final String PRODUCT_EXISTS_MESSAGE = "Um produto com esse nome já existe";
    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Produto não encontrado";

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(ProductRequestDto productRequestDto) {
        validateProductNameUniqueness(productRequestDto.name());
        Product newProduct = new Product();
        BeanUtils.copyProperties(productRequestDto, newProduct);
        productRepository.save(newProduct);
    }

    @Override
    public void updateProduct(UUID productId, ProductRequestDto productRequestDto) {
        Product existingProduct = getProductById(productId);

        if (!existingProduct.getName().equalsIgnoreCase(productRequestDto.name())) {
            validateProductNameUniqueness(productRequestDto.name());
        }

        BeanUtils.copyProperties(productRequestDto, existingProduct);
        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID productId) {
        Product product = getProductById(productId);
        productRepository.delete(product);
    }

    @Override
    public PaginatedResponseDto<ProductResponseDto> getProducts(ProductSearchFilterDto filter, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        Specification<Product> specification = ProductSpecification.fromFilter(filter);
        Page<Product> productPage = productRepository.findAll(specification, pageable);

        return new PaginatedResponseDto<>(
                productPage.map(Mapper::toProductResponseDto).getContent(),
                productPage.getTotalPages(),
                productPage.getTotalElements(),
                productPage.getNumber()
        );
    }

    private Product getProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND_MESSAGE));
    }

    private void validateProductNameUniqueness(String productName) {
        productRepository.findByNameIgnoreCase(productName)
                .ifPresent(existingProduct -> {
                    throw new EntityExistsException(PRODUCT_EXISTS_MESSAGE);
                });
    }
}
