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
    public Collection<Category> addToProduct(Product product, Collection<Category> categories) {
        product.getCategories().addAll(categories);
        categories.forEach(c -> c.getProducts().add(product));

        entityManager.persist(product);

        return categories;
    }

    @Transactional
    @Override
    public Collection<Category> removeFromProduct(Product product, Collection<Category> categories) {
        categories.retainAll(product.getCategories());
        product.getCategories().removeAll(categories);
        categories.forEach(c -> c.getProducts().remove(product));

        entityManager.persist(product);

        return categories;
    }

}
