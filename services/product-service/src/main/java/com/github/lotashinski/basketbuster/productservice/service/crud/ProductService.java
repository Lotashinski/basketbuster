package com.github.lotashinski.basketbuster.productservice.service.crud;

import com.github.lotashinski.basketbuster.productservice.dto.ProductCriteria;
import com.github.lotashinski.basketbuster.productservice.dto.ProductGetDto;
import com.github.lotashinski.basketbuster.productservice.dto.ProductPostDto;
import org.springframework.data.domain.Slice;

public interface ProductService {

    Slice<ProductGetDto> get(ProductCriteria criteria);

    ProductGetDto get(Long id);

    ProductGetDto create(ProductPostDto categoryDto);

    ProductGetDto update(Long id, ProductPostDto categoryDto);

    void delete(Long id);

}
