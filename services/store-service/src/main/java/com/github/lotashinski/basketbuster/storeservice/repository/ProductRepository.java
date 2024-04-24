package com.github.lotashinski.basketbuster.storeservice.repository;

import com.github.lotashinski.basketbuster.storeservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
