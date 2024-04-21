package com.github.lotashinski.basketbuster.productservice.controller;

import com.github.lotashinski.basketbuster.productservice.dto.ProductCriteria;
import com.github.lotashinski.basketbuster.productservice.dto.ProductGetDto;
import com.github.lotashinski.basketbuster.productservice.dto.ProductPostDto;
import com.github.lotashinski.basketbuster.productservice.service.crud.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Slice<ProductGetDto>> getPage(ProductCriteria criteria) {
        return ResponseEntity.ok(productService.get(criteria));
    }

    @PostMapping
    public ResponseEntity<ProductGetDto> create(@RequestBody ProductPostDto productDto) {
        return ResponseEntity.ok(productService.create(productDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGetDto> get(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductGetDto> update(@PathVariable Long id, @RequestBody ProductPostDto categoryDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        return null;
    }
}
