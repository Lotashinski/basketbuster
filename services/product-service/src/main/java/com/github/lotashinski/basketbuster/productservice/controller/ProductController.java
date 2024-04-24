package com.github.lotashinski.basketbuster.productservice.controller;

import com.github.lotashinski.basketbuster.productservice.dto.*;
import com.github.lotashinski.basketbuster.productservice.service.crud.ProductCategoryService;
import com.github.lotashinski.basketbuster.productservice.service.crud.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final ProductCategoryService productCategoryService;


    @GetMapping
    public ResponseEntity<Slice<ProductItemDto>> getPage(ProductCriteria criteria) {
        return ResponseEntity.ok(productService.get(criteria));
    }

    @PostMapping
    public ResponseEntity<ProductGetDto> create(@RequestBody ProductPostDto productDto) {
        return ResponseEntity.ok(productService.create(productDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGetDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(productService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductGetDto> update(@PathVariable Long id, @RequestBody ProductPostDto categoryDto) {
        return ResponseEntity.ok(productService.update(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<Collection<CategoryItemGetDto>> getCategories(@PathVariable Long id) {
        return ResponseEntity.ok(productCategoryService.getProductCategories(id));
    }

    @PostMapping("/{id}/categories")
    public ResponseEntity<Collection<CategoryItemGetDto>> addCategories(@PathVariable Long id,
                                                                        @RequestBody Collection<Long> categories) {
        return ResponseEntity.ok(productCategoryService.addCategories(id, categories));
    }

    @DeleteMapping("/{id}/categories")
    public ResponseEntity<Void> deleteCategories(@PathVariable Long id, @RequestBody Collection<Long> categories) {
        productCategoryService.deleteCategories(id, categories);

        return ResponseEntity.noContent().build();
    }

}
