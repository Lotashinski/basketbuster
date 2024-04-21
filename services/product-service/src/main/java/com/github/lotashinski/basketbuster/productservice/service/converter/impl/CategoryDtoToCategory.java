package com.github.lotashinski.basketbuster.productservice.service.converter.impl;

import com.github.lotashinski.basketbuster.productservice.dto.CategoryPostDto;
import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.exception.HttpNotFoundException;
import com.github.lotashinski.basketbuster.productservice.repository.CategoryRepositoryInterface;
import com.github.lotashinski.basketbuster.productservice.service.converter.ConverterInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryDtoToCategory implements ConverterInterface<CategoryPostDto, Category> {

    @Value("${crud.categories}")
    private String categoriesUrl;

    private final CategoryRepositoryInterface categoryRepository;

    @Override
    public Category convert(CategoryPostDto categoryDto, Category category) {
        category.setTitle(categoryDto.getTitle());
        category.setOwner(extractOwner(categoryDto));

        return category;
    }

    public Category extractOwner(CategoryPostDto categoryDto) {
        String url = categoryDto.getOwner();

        if (url == null) {
            return null;
        }

        Long id = Long.parseLong(url.replace(categoriesUrl  + "/", ""));
        return categoryRepository.findById(id)
                .orElseThrow(() -> new HttpNotFoundException(String.format("Category with id %s not found", id)));
    }
}
