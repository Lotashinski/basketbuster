package com.github.lotashinski.basketbuster.productservice.service.converter.impl;

import com.github.lotashinski.basketbuster.productservice.dto.CategoryItemGetDto;
import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.service.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class CategoryToCategoryItemGetDtoConverter implements Converter<Category, CategoryItemGetDto> {

    @Override
    public CategoryItemGetDto convert(Category category, CategoryItemGetDto categoryItemGetDto) {
        categoryItemGetDto.setId(category.getId());
        categoryItemGetDto.setTitle(category.getTitle());

        return categoryItemGetDto;
    }

}
