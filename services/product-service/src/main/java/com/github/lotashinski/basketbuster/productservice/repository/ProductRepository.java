package com.github.lotashinski.basketbuster.productservice.repository;

import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Slice<Product> findByCategories(Set<Category> category, Pageable pageable);

    Slice<Product> findByCategory(Category category, Pageable pageable);

}
