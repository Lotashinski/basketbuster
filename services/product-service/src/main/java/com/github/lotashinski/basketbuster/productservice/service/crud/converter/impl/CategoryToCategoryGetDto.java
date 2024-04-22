package com.github.lotashinski.basketbuster.productservice.service.crud.converter.impl;

import com.github.lotashinski.basketbuster.productservice.dto.CategoryGetDto;
import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.service.crud.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class CategoryToCategoryGetDto implements Converter<Category, CategoryGetDto> {

    @Override
    public CategoryGetDto convert(Category category, CategoryGetDto categoryDto) {
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setOwnerId(extractOwnerUrl(category));
        categoryDto.setChildrenCount((long) category.getChildren().size());

        return categoryDto;
    }

    private Long extractOwnerUrl(Category category) {
        Category owner = category.getOwner();
        if (owner == null) {
            return null;
        }

        return owner.getId();
    }

}
