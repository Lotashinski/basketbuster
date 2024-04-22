package com.github.lotashinski.basketbuster.productservice.service.crud.converter.impl;

import com.github.lotashinski.basketbuster.productservice.dto.ProductPostDto;
import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.service.crud.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class ProductPostDtoToProduct implements Converter<ProductPostDto, Product> {

    @Override
    public Product convert(ProductPostDto productPostDto, Product product) {
        product.setTitle(productPostDto.getTitle());
        product.setDescription(productPostDto.getDescription());

        return product;
    }

}
