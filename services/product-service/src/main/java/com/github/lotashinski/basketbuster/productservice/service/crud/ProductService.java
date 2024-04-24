package com.github.lotashinski.basketbuster.productservice.service.crud;

import com.github.lotashinski.basketbuster.productservice.dto.*;
import org.springframework.data.domain.Slice;


public interface ProductService {

    Slice<ProductItemDto> get(ProductCriteria criteria);

    ProductGetDto get(Long id);

    ProductGetDto create(ProductPostDto dto);

    ProductGetDto update(Long id, ProductPostDto dto);

    void delete(Long id);

}
