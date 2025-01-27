package com.ga5000.api.ecommerce.service.product;

import com.ga5000.api.ecommerce.domain.product.ProductSearchDto;
import com.ga5000.api.ecommerce.dto.page.PageRequestDto;
import com.ga5000.api.ecommerce.dto.product.ProductRequestDto;
import com.ga5000.api.ecommerce.dto.product.ProductResponseDto;
import com.ga5000.api.ecommerce.dto.product.SearchParams;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IProductService {
    void createProduct(ProductRequestDto productRequestDto);
    void updateProduct(UUID productId, ProductRequestDto productRequestDto);
    void deleteProduct(UUID productId);

    ProductResponseDto getProduct(UUID productId);

    Page<ProductSearchDto> searchProducts(SearchParams params, PageRequestDto pageRequest);

}
