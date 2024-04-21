package com.github.lotashinski.basketbuster.productservice.service.impl;

import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.repository.CategoryRepositoryInterface;
import com.github.lotashinski.basketbuster.productservice.service.CategoryServiceInterface;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceInterface {

    private final CategoryRepositoryInterface categoryRepository;


    @Transactional
    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public Category deleteCategory(Category category) {
        categoryRepository.delete(category);
        return category;
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
