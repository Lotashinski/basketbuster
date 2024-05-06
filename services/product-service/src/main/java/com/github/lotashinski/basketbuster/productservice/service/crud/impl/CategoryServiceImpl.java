package com.github.lotashinski.basketbuster.productservice.service.crud.impl;

import com.github.lotashinski.basketbuster.productservice.dto.CategoryCriteria;
import com.github.lotashinski.basketbuster.productservice.dto.CategoryGetDto;
import com.github.lotashinski.basketbuster.productservice.dto.CategoryPostDto;
import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.service.converter.Converter;
import com.github.lotashinski.basketbuster.productservice.service.crud.CategoryService;
import com.github.lotashinski.basketbuster.productservice.service.crud.exception.DataConflictException;
import com.github.lotashinski.basketbuster.productservice.service.crud.exception.NotFoundException;
import com.github.lotashinski.basketbuster.productservice.service.jpa.JpaCategoryService;
import com.github.lotashinski.basketbuster.productservice.service.jpa.exception.CategoryHierarchyException;
import com.github.lotashinski.basketbuster.productservice.service.jpa.exception.CategoryTitleAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public final class CategoryServiceImpl implements CategoryService {

    private final JpaCategoryService categoryService;

    private final Converter<Category, CategoryGetDto> converterFotCategoryToCategoryGetDto;

    private final Converter<CategoryPostDto, Category> converterFotCategoryPostDtoToCategory;


    @Override
    public Slice<CategoryGetDto> get(CategoryCriteria criteria) {
        Slice<Category> categories = criteria.getOwnerId() == null
                ? mainCategories(criteria)
                : subCategories(criteria);

        return categories
                .map(c -> converterFotCategoryToCategoryGetDto.convert(c, new CategoryGetDto()));
    }

    @Override
    public CategoryGetDto get(Long id) {
        Optional<Category> categoryOptional = categoryService.get(id);
        return categoryOptional
                .map(c -> converterFotCategoryToCategoryGetDto.convert(c, new CategoryGetDto()))
                .orElseThrow(() -> createNotFoundException(id));
    }

    @Override
    public CategoryGetDto create(CategoryPostDto categoryDto) {
        try {
            Category newCategory = converterFotCategoryPostDtoToCategory.convert(categoryDto, new Category());
            Category createdCategory = categoryService.create(newCategory);

            return converterFotCategoryToCategoryGetDto.convert(createdCategory, new CategoryGetDto());
        } catch (CategoryHierarchyException | CategoryTitleAlreadyExistsException che) {
            throw new DataConflictException(che.getMessage(), che);
        }
    }

    @Override
    public CategoryGetDto update(Long id, CategoryPostDto categoryDto) {
        try {
            return categoryService.get(id)
                    .map(c -> converterFotCategoryPostDtoToCategory.convert(categoryDto, c))
                    .map(categoryService::update)
                    .map(c -> converterFotCategoryToCategoryGetDto.convert(c, new CategoryGetDto()))
                    .orElseThrow(() -> createNotFoundException(id));
        } catch (CategoryHierarchyException | CategoryTitleAlreadyExistsException che) {
            throw new DataConflictException(che.getMessage(), che);
        }
    }

    @Override
    public void delete(Long id) {
        categoryService.get(id)
                .map(categoryService::delete)
                .orElseThrow(() -> createNotFoundException(id));
    }

    private Slice<Category> mainCategories(CategoryCriteria criteria) {
        return categoryService
                .getMainCategories(PageRequestExtractor.extractPageRequest(criteria));
    }

    private Slice<Category> subCategories(CategoryCriteria criteria) {
        Category owner = categoryService.get(criteria.getOwnerId())
                .orElseThrow(() -> createNotFoundException(criteria.getOwnerId()));

        return categoryService
                .getSubCategories(owner, PageRequestExtractor.extractPageRequest(criteria));
    }


    private static NotFoundException createNotFoundException(Long id) {
        return new NotFoundException(String.format("Category with id %s not found", id));
    }

}
