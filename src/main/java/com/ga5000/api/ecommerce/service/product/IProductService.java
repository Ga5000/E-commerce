package com.ga5000.api.ecommerce.service.product;

import com.ga5000.api.ecommerce.dto.product.ProductRequest;
import com.ga5000.api.ecommerce.dto.product.ProductResponseInfo;
import com.ga5000.api.ecommerce.dto.product.search.SearchProductParams;
import com.ga5000.api.ecommerce.dto.product.search.SearchProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    void createProduct(ProductRequest productRequest,  List<MultipartFile> files);
    void updateProduct(UUID productId, ProductRequest productRequest);
    void deleteProduct(UUID productId);

    void updateDiscount(UUID productId, int discount);
   Page<SearchProductResponse> searchProducts(SearchProductParams params, Pageable pageable, Sort.Direction direction);

    ProductResponseInfo getProductInfo(UUID productId);



}
