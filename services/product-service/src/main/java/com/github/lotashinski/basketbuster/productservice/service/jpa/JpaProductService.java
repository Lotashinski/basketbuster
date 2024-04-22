package com.github.lotashinski.basketbuster.productservice.service.jpa;

import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.service.jpa.criteria.JpaProductCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface JpaProductService {

    Product create(Product product);

    Product update(Product product);

    Product delete(Product product);

    Optional<Product> get(Long id);

    Slice<Product> getByCriteria(JpaProductCriteria criteria, Pageable pageable);

}
