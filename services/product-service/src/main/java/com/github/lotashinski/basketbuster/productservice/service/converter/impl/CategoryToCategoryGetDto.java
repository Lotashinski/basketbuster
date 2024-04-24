package com.github.lotashinski.basketbuster.productservice.service.converter.impl;

import com.github.lotashinski.basketbuster.productservice.dto.CategoryGetDto;
import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.service.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class CategoryToCategoryGetDto implements Converter<Category, CategoryGetDto> {

    @Override
    public CategoryGetDto convert(Category category, CategoryGetDto categoryDto) {
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setOwnerId(extractOwnerId(category));
        categoryDto.setChildrenCount((long) category.getChildren().size());

        return categoryDto;
    }

    private Long extractOwnerId(Category category) {
        Category owner = category.getOwner();
        if (owner == null) {
            return null;
        }

        return owner.getId();
    }

}
