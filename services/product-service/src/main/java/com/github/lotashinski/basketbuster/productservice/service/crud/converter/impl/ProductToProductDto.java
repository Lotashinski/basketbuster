package com.github.lotashinski.basketbuster.productservice.service.crud.converter.impl;

import com.github.lotashinski.basketbuster.productservice.dto.ProductGetDto;
import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.service.crud.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductDto implements Converter<Product, ProductGetDto> {

    @Override
    public ProductGetDto convert(Product product, ProductGetDto productGetDto) {
        productGetDto.setId(product.getId());
        productGetDto.setTitle(product.getTitle());
        productGetDto.setDescription(product.getDescription());

        return productGetDto;
    }

}
