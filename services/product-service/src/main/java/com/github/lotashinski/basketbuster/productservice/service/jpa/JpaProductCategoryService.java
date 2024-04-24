package com.github.lotashinski.basketbuster.productservice.service.jpa;

import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.entity.Product;

import java.util.Collection;

public interface JpaProductCategoryService {

    Collection<Category> getByProduct(Product product);

    Collection<Category> addToProduct(Product product, Collection<Category> category);

    Collection<Category> removeFromProduct(Product product, Collection<Category> category);

}
