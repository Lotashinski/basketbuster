package com.github.lotashinski.basketbuster.productservice.repository;

import com.github.lotashinski.basketbuster.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
