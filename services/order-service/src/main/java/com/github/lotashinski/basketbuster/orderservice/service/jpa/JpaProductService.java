package com.github.lotashinski.basketbuster.orderservice.service.jpa;

import com.github.lotashinski.basketbuster.orderservice.entity.Product;

import java.util.Optional;

public interface JpaProductService {

    Product create(Product product);

    Product update(Product product);

    Product delete(Product product);

    Optional<Product> get(Long id);

}
