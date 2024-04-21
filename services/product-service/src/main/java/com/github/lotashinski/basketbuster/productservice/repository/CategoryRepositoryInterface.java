package com.github.lotashinski.basketbuster.productservice.repository;

import com.github.lotashinski.basketbuster.productservice.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepositoryInterface extends JpaRepository<Category, Long> {

    List<Category> findAllByOwnerIsNull(Pageable pageable);

    List<Category> findAllByOwner(Category owner, Pageable pageable);

}
