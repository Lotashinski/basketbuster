package com.github.lotashinski.basketbuster.productservice.service;

import com.github.lotashinski.basketbuster.productservice.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceInterface {

    Category createCategory(Category category);

    Category updateCategory(Category category);

    Category deleteCategory(Category category);

    Optional<Category> getCategoryById(Long id);

    List<Category> getAllCategories();
}
