package com.github.lotashinski.basketbuster.productservice.service.crud;

import com.github.lotashinski.basketbuster.productservice.dto.ProductCriteria;
import com.github.lotashinski.basketbuster.productservice.dto.ProductGetDto;
import com.github.lotashinski.basketbuster.productservice.dto.ProductItemDto;
import com.github.lotashinski.basketbuster.productservice.dto.ProductPostDto;
import org.springframework.data.domain.Slice;

public interface ProductService {

    Slice<ProductItemDto> get(ProductCriteria criteria);

    ProductGetDto get(Long id);

    ProductGetDto create(ProductPostDto dto);

    ProductGetDto update(Long id, ProductPostDto dto);

    void delete(Long id);

}
