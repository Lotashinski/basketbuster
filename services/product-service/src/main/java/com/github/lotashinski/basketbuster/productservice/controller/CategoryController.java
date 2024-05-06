package com.github.lotashinski.basketbuster.productservice.controller;

import com.github.lotashinski.basketbuster.productservice.dto.CategoryCriteria;
import com.github.lotashinski.basketbuster.productservice.dto.CategoryGetDto;
import com.github.lotashinski.basketbuster.productservice.dto.CategoryPostDto;
import com.github.lotashinski.basketbuster.productservice.service.crud.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping
    public ResponseEntity<Slice<CategoryGetDto>> getPage(CategoryCriteria criteria) {
        return ResponseEntity.ok(categoryService.get(criteria));
    }

    @PostMapping
    public ResponseEntity<CategoryGetDto> create(@RequestBody CategoryPostDto categoryDto) {
        return ResponseEntity.ok(categoryService.create(categoryDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryGetDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryGetDto> update(@PathVariable Long id, @RequestBody CategoryPostDto categoryDto) {
        return ResponseEntity.ok(categoryService.update(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
