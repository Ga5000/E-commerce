package com.ga5000.api.ecommerce.controller.product;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.product.ProductRequest;
import com.ga5000.api.ecommerce.dto.product.ProductResponseInfo;
import com.ga5000.api.ecommerce.dto.product.search.SearchProductParams;
import com.ga5000.api.ecommerce.dto.product.search.SearchProductResponse;
import com.ga5000.api.ecommerce.service.product.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService productService;
    private final PagedResourcesAssembler<SearchProductResponse> pagedResourcesAssembler;

    public ProductController(IProductService productService, PagedResourcesAssembler<SearchProductResponse> pagedResourcesAssembler) {
        this.productService = productService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> createProduct(
            @RequestPart("product") ProductRequest productRequest,
            @RequestPart("files") List<MultipartFile> files) {

            productService.createProduct(productRequest, files);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("success", "Product created successfully"));
    }


    @PutMapping("/update/{productId}")
    public ResponseEntity<Map<String, String>> updateProduct(@PathVariable UUID productId,
                                              @RequestBody ProductRequest productRequest) {
        productService.updateProduct(productId, productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("success", "Product updated successfully"));
    }

    @DeleteMapping("delete/{productId}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("success", "Product deleted successfully"));
    }

    @PatchMapping("/discount/{productId}")
    public ResponseEntity<Map<String, String>> updateDiscount(@PathVariable UUID productId, @RequestParam int discount) {
        productService.updateDiscount(productId, discount);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Collections.singletonMap("success", "Product discount updated successfully"));
    }

    @GetMapping("/search")
    public ResponseEntity<PagedModel<EntityModel<SearchProductResponse>>> searchProducts(
            @RequestBody SearchProductParams params,
            @PageableDefault(size = 20) Pageable pageable,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction) {

        Page<SearchProductResponse> products = productService.searchProducts(params, pageable, direction);

        return ResponseEntity.status(HttpStatus.OK)
                .body(pagedResourcesAssembler.toModel(products, product -> EntityModel.of(product,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class)
                                        .getProductInfo(product.getProductId()))
                                .withSelfRel())));
    }

    @GetMapping("/info/{productId}")
    public ResponseEntity<ProductResponseInfo> getProductInfo(@PathVariable UUID productId) {
        return ResponseEntity.ok(productService.getProductInfo(productId));
    }
}


