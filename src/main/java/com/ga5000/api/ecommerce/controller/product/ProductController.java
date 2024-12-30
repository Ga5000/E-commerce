package com.ga5000.api.ecommerce.controller.product;

import com.ga5000.api.ecommerce.dto.product.ProductRequestDto;
import com.ga5000.api.ecommerce.dto.product.ProductResponseDto;
import com.ga5000.api.ecommerce.dto.product.ProductSearchFilterDto;
import com.ga5000.api.ecommerce.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> addProduct(@RequestBody @Valid ProductRequestDto productRequestDto){
        productService.createProduct(productRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Produto adicionado com sucesso!");
    }

    @PutMapping("/update?id={productId}")
    public ResponseEntity<String> updateProduct(@PathVariable UUID productId,
                                                @RequestBody @Valid ProductRequestDto productRequestDto){
        productService.updateProduct(productId, productRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("Produto atualizado com sucesso!");
    }

    @DeleteMapping("/delete?id={productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID productId){
        productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponseDto>> searchProducts(@RequestParam Integer page,
                                                                   @RequestParam Sort.Direction sortDirection,
                                                                   @RequestBody @Valid ProductSearchFilterDto filter) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts(page, filter, sortDirection));
    }


}
