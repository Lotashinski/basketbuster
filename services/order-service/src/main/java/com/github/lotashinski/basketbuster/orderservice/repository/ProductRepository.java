package com.github.lotashinski.basketbuster.orderservice.repository;

import com.github.lotashinski.basketbuster.orderservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
