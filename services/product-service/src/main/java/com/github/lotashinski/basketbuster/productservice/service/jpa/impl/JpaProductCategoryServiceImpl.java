package com.github.lotashinski.basketbuster.productservice.service.jpa.impl;

import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.service.jpa.JpaProductCategoryService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class JpaProductCategoryServiceImpl implements JpaProductCategoryService {

    private final EntityManager entityManager;


    @Override
    public Collection<Category> getByProduct(Product product) {
        return product.getCategories();
    }

    @Transactional
    @Override
    public Collection<Category> addToProduct(Product product, Collection<Category> category) {
        product.getCategories().addAll(category);

        entityManager.persist(product);
        entityManager.flush();

        return category;
    }

    @Transactional
    @Override
    public Collection<Category> removeFromProduct(Product product, Collection<Category> category) {
        category.retainAll(product.getCategories());
        product.getCategories().removeAll(category);

        entityManager.persist(product);
        entityManager.flush();

        return category;
    }

}
