package com.ga5000.api.ecommerce.controller.category;

import com.ga5000.api.ecommerce.dto.category.CategoryResponseDto;
import com.ga5000.api.ecommerce.service.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> addCategory(@RequestBody @Valid String categoryName){
        categoryService.createCategory(categoryName);

        return ResponseEntity.status(HttpStatus.CREATED).body("Categoria adicionada com sucesso!");
    }

    @PutMapping("/update?id={categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable UUID categoryId,
                                                @RequestBody @Valid String categoryName){
        categoryService.updateCategory(categoryId, categoryName);

        return ResponseEntity.status(HttpStatus.OK).body("Categoria atualizada com sucesso!");
    }

    @DeleteMapping("/delete?id={categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID categoryId){
        categoryService.deleteCategory(categoryId);

        return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada com sucesso!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDto>> getCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }

}
