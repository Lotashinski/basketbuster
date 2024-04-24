package com.github.lotashinski.basketbuster.productservice.service.crud;

import com.github.lotashinski.basketbuster.productservice.dto.CategoryItemGetDto;

import java.util.Collection;

public interface ProductCategoryService {

    Collection<CategoryItemGetDto> getCategories(Long productId);

    Collection<CategoryItemGetDto> deleteCategories(Long productId, Collection<Long> categories);

    Collection<CategoryItemGetDto> addCategories(Long productId, Collection<Long> categories);

}
