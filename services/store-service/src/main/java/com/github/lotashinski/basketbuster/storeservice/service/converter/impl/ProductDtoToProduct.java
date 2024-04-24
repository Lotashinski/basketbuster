package com.github.lotashinski.basketbuster.storeservice.service.converter.impl;

import com.github.lotashinski.basketbuster.storeservice.entity.Product;
import com.github.lotashinski.basketbuster.storeservice.service.converter.Converter;
import com.github.lotashinski.basketbuster.storeservice.service.message.broker.dto.ProductDto;
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
