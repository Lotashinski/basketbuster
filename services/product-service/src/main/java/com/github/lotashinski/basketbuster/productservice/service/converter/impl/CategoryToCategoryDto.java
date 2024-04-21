package com.github.lotashinski.basketbuster.productservice.service.converter.impl;

import com.github.lotashinski.basketbuster.productservice.dto.CategoryGetDto;
import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.service.converter.ConverterInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
class CategoryToCategoryDto implements ConverterInterface<Category, CategoryGetDto> {

    @Value("${crud.categories}")
    private String categoriesUrl;

    @Override
    public CategoryGetDto convert(Category category, CategoryGetDto categoryDto) {
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setOwner(extractOwnerUrl(category));
        categoryDto.setChildrenCount((long) category.getChildren().size());

        return categoryDto;
    }

    private String extractOwnerUrl(Category category) {
        Category owner = category.getOwner();
        if (owner == null) {
            return null;
        }

        return String.format("%s/%s", categoriesUrl, owner.getId());
    }

}
