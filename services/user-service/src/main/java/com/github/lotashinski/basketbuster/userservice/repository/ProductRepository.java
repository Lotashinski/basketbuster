package com.github.lotashinski.basketbuster.userservice.repository;

import com.github.lotashinski.basketbuster.userservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
