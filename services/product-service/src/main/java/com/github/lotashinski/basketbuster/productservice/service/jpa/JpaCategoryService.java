package com.github.lotashinski.basketbuster.productservice.service.jpa;

import com.github.lotashinski.basketbuster.productservice.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface JpaCategoryService {

    Category create(Category category);

    Category update(Category category);

    Category delete(Category category);

    Optional<Category> get(Long id);

    Slice<Category> getMainCategories(Pageable pageable);

    Slice<Category> getSubCategories(Category owner, Pageable pageable);

    Set<Category> getAllByIds(Collection<Long> ids);

}
