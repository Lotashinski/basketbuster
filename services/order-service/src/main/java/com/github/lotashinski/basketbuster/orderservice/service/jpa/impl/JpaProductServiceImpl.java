package com.github.lotashinski.basketbuster.orderservice.service.jpa.impl;

import com.github.lotashinski.basketbuster.orderservice.entity.Product;
import com.github.lotashinski.basketbuster.orderservice.repository.ProductRepository;
import com.github.lotashinski.basketbuster.orderservice.service.jpa.JpaProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaProductServiceImpl implements JpaProductService {

    private final ProductRepository productRepository;


    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product delete(Product product) {
        productRepository.delete(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return productRepository.findById(id);
    }
}
