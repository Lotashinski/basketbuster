package com.github.lotashinski.basketbuster.productservice.service.jpa.impl;

import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.repository.CategoryRepository;
import com.github.lotashinski.basketbuster.productservice.service.jpa.JpaCategoryService;
import com.github.lotashinski.basketbuster.productservice.service.jpa.exception.CategoryHierarchyException;
import com.github.lotashinski.basketbuster.productservice.service.jpa.exception.CategoryTitleAlreadyExistsException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JpaCategoryServiceImpl implements JpaCategoryService {

    @NonNull
    private CategoryRepository categoryRepository;


    @Transactional
    @Override
    public Category create(Category category) {
        String title = category.getTitle();
        categoryRepository.findByTitle(title)
                .ifPresent(c -> {
                    throw createAlreadyExistsException(title);
                });

        if (!isHierarchyValid(category)) {
            throw new CategoryHierarchyException("Infinity loop detect!");
        }

        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        String title = category.getTitle();
        categoryRepository.findByTitle(title)
                .ifPresent(c -> {
                    if (!c.getId().equals(category.getId())) {
                        throw createAlreadyExistsException(title);
                    }
                });

        if (!isHierarchyValid(category)) {
            throw new CategoryHierarchyException("Infinity loop detect!");
        }

        categoryRepository.save(category);
        return category;
    }

    @Transactional
    @Override
    public Category delete(Category category) {
        categoryRepository.delete(category);
        return category;
    }

    @Override
    public Optional<Category> get(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Slice<Category> getMainCategories(Pageable pageable) {
        return categoryRepository.findAllByOwnerIsNull(pageable);
    }

    @Override
    public Slice<Category> getSubCategories(Category owner, Pageable pageable) {
        return categoryRepository.findAllByOwner(owner, pageable);
    }

    private boolean isHierarchyValid(Category category) {
        Set<Long> ids = new HashSet<>();
        while (category != null) {
            if (ids.contains(category.getId())) {
                return false;
            }
            ids.add(category.getId());
        }

        return true;
    }


    private static CategoryTitleAlreadyExistsException createAlreadyExistsException(String title) {
        return new CategoryTitleAlreadyExistsException("Category with title '" + title + "' already exists");
    }
}
