package com.github.lotashinski.basketbuster.productservice.controller;

import com.github.lotashinski.basketbuster.productservice.dto.CategoryGetDto;
import com.github.lotashinski.basketbuster.productservice.dto.CategoryPostDto;
import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.service.CategoryServiceInterface;
import com.github.lotashinski.basketbuster.productservice.service.converter.ConverterInterface;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("${crud.categories}")
@RequiredArgsConstructor
public class CategoryController {

    private CategoryServiceInterface categoryService;

    private ConverterInterface<Category, CategoryGetDto> converterFotCategoryToCategoryGetDto;

    private ConverterInterface<CategoryPostDto, Category> converterFotCategoryPostDtoToCategory;

    @GetMapping
    public ResponseEntity<List<CategoryGetDto>> getPage() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryGetDto> categoryDtoes = categories.stream()
                .map(c -> converterFotCategoryToCategoryGetDto.convert(c, new CategoryGetDto()))
                .toList();

        return ResponseEntity.ok(categoryDtoes);
    }

    @PostMapping
    public ResponseEntity<CategoryGetDto> create(@RequestBody CategoryPostDto categoryDto) {
        Category newCategory = converterFotCategoryPostDtoToCategory.convert(categoryDto, new Category());
        Category createdCategory = categoryService.createCategory(newCategory);

        CategoryGetDto responseDto = converterFotCategoryToCategoryGetDto.convert(createdCategory, new CategoryGetDto());
        return ResponseEntity.ok(responseDto);
    }

}
