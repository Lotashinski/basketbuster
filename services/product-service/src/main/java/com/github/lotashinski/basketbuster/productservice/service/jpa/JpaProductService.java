package com.github.lotashinski.basketbuster.productservice.service.jpa;

import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface JpaProductService {

    Product create(Product category);

    Product update(Product category);

    Product delete(Product category);

    Optional<Product> get(Long id);

    Slice<Product> findWithCategory(Category category, Pageable pageable);

}
