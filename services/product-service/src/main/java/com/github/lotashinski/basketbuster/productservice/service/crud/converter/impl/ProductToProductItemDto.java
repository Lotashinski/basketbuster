package com.github.lotashinski.basketbuster.productservice.service.crud.converter.impl;

import com.github.lotashinski.basketbuster.productservice.dto.ProductItemDto;
import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.service.crud.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductItemDto implements Converter<Product, ProductItemDto> {

    @Override
    public ProductItemDto convert(Product product, ProductItemDto productItemDto) {
        productItemDto.setId(product.getId());
        productItemDto.setTitle(product.getTitle());

        return productItemDto;
    }

}
