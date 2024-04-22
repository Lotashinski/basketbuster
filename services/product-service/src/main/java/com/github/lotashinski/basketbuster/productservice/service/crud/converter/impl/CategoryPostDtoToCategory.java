package com.github.lotashinski.basketbuster.productservice.service.crud.converter.impl;

import com.github.lotashinski.basketbuster.productservice.dto.CategoryPostDto;
import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.repository.CategoryRepository;
import com.github.lotashinski.basketbuster.productservice.service.crud.converter.Converter;
import com.github.lotashinski.basketbuster.productservice.service.crud.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class CategoryPostDtoToCategory implements Converter<CategoryPostDto, Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public Category convert(CategoryPostDto categoryDto, Category category) {
        category.setTitle(categoryDto.getTitle());
        category.setOwner(extractOwner(categoryDto));

        return category;
    }

    public Category extractOwner(CategoryPostDto categoryDto) {
        Long ownerId = categoryDto.getOwnerId();

        if (ownerId == null) {
            return null;
        }

        return categoryRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException(String.format("Category with id %s not found", ownerId)));
    }
}
