package com.github.lotashinski.basketbuster.userservice.service.converter.impl;

import com.github.lotashinski.basketbuster.userservice.entity.Product;
import com.github.lotashinski.basketbuster.userservice.service.converter.Converter;
import com.github.lotashinski.basketbuster.userservice.service.message.broker.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoToProduct implements Converter<ProductDto, Product> {

    @Override
    public Product convert(ProductDto productDto, Product product) {
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());

        return product;
    }

}