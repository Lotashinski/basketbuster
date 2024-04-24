package com.github.lotashinski.basketbuster.productservice.service.crud.impl;

import com.github.lotashinski.basketbuster.productservice.dto.CategoryItemGetDto;
import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.service.converter.Converter;
import com.github.lotashinski.basketbuster.productservice.service.crud.ProductCategoryService;
import com.github.lotashinski.basketbuster.productservice.service.crud.exception.NotFoundException;
import com.github.lotashinski.basketbuster.productservice.service.jpa.JpaCategoryService;
import com.github.lotashinski.basketbuster.productservice.service.jpa.JpaProductCategoryService;
import com.github.lotashinski.basketbuster.productservice.service.jpa.JpaProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    protected final JpaCategoryService jpaCategoryService;

    private final JpaProductService jpaProductService;

    private final JpaProductCategoryService jpaProductCategoryService;

    private final Converter<Category, CategoryItemGetDto> categoryItemGetDtoConverter;


    @Override
    public Collection<CategoryItemGetDto> getProductCategories(Long productId) {
        log.info("Get product categories for {}", productId);

        Collection<Category> categories = jpaProductService.get(productId)
                .map(jpaProductCategoryService::getByProduct)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", productId)));

        return categories.stream()
                .map(c -> categoryItemGetDtoConverter.convert(c, new CategoryItemGetDto()))
                .toList();
    }

    @Override
    public Collection<CategoryItemGetDto> deleteCategories(Long productId, Collection<Long> categoriesIds) {
        log.info("Delete product categories for {}", productId);

        Product product = getProduct(productId);
        Collection<Category> categories = getCategories(categoriesIds);

        return jpaProductCategoryService.removeFromProduct(product, categories).stream()
                .map(c -> categoryItemGetDtoConverter.convert(c, new CategoryItemGetDto()))
                .toList();
    }

    @Override
    public Collection<CategoryItemGetDto> addCategories(Long productId, Collection<Long> categoriesIds) {
        log.info("Add product categories for {}", productId);

        Product product = getProduct(productId);
        Collection<Category> categories = getCategories(categoriesIds);

        return jpaProductCategoryService.addToProduct(product, categories).stream()
                .map(c -> categoryItemGetDtoConverter.convert(c, new CategoryItemGetDto()))
                .toList();
    }

    private Product getProduct(Long productId) {
        log.info("Get product with id {}", productId);
        return jpaProductService
                .get(productId)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", productId)));
    }

    private Collection<Category> getCategories(Collection<Long> categoriesIds) {
        log.info("Get categories with id {}", categoriesIds);
        return jpaCategoryService.getAllByIds(categoriesIds);
    }

}
