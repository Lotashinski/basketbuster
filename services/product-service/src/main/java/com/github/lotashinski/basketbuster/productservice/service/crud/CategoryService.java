package com.github.lotashinski.basketbuster.productservice.service.crud;

import com.github.lotashinski.basketbuster.productservice.dto.CategoryCriteria;
import com.github.lotashinski.basketbuster.productservice.dto.CategoryGetDto;
import com.github.lotashinski.basketbuster.productservice.dto.CategoryPostDto;
import org.springframework.data.domain.Slice;

public interface CategoryService {

    Slice<CategoryGetDto> get(CategoryCriteria criteria);

    CategoryGetDto get(Long id);

    CategoryGetDto create(CategoryPostDto dto);

    CategoryGetDto update(Long id, CategoryPostDto dto);

    void delete(Long id);

}
