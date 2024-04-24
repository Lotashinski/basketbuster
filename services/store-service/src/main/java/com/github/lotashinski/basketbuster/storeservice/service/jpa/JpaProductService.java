package com.github.lotashinski.basketbuster.storeservice.service.jpa;

import com.github.lotashinski.basketbuster.storeservice.entity.Product;

import java.util.Optional;

public interface JpaProductService {

    Product create(Product product);

    Product update(Product product);

    Product delete(Product product);

    Optional<Product> get(Long id);

}
