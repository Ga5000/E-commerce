package com.ga5000.api.ecommerce.controller.product.category;

import com.ga5000.api.ecommerce.dto.category.CategoryRequest;
import com.ga5000.api.ecommerce.dto.category.CategoryResponse;
import com.ga5000.api.ecommerce.service.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("success", "Category created successfully"));
    }

    @PostMapping("/create-categories")
    public ResponseEntity<Map<String, String>> createCategories(@RequestBody @Valid List<CategoryRequest> categoryRequests) {
        categoryService.createCategories(categoryRequests);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("success", "Categories created successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateCategory(@PathVariable UUID id, @RequestBody @Valid CategoryRequest categoryRequest) {
        categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("success", "Category updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("success", "Category deleted successfully"));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }


}
