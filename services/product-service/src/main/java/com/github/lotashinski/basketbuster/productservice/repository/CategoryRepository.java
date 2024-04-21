package com.github.lotashinski.basketbuster.productservice.repository;

import com.github.lotashinski.basketbuster.productservice.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByTitle(String title);

    Page<Category> findAllByOwnerIsNull(Pageable pageable);

    Page<Category> findAllByOwner(Category owner, Pageable pageable);

}
