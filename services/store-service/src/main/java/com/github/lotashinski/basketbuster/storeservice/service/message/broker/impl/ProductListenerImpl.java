package com.github.lotashinski.basketbuster.storeservice.service.message.broker.impl;

import com.github.lotashinski.basketbuster.storeservice.entity.Product;
import com.github.lotashinski.basketbuster.storeservice.service.converter.Converter;
import com.github.lotashinski.basketbuster.storeservice.service.jpa.JpaProductService;
import com.github.lotashinski.basketbuster.storeservice.service.message.broker.ProductListener;
import com.github.lotashinski.basketbuster.storeservice.service.message.broker.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductListenerImpl implements ProductListener {

    private final Converter<ProductDto, Product> productConverter;

    private final JpaProductService productService;


    @Override
    public void onCreated(ProductDto product) {
        Product newProduct = productConverter.convert(product, new Product());
        productService.create(newProduct);
    }

    @Override
    public void onUpdated(ProductDto product) {
        Optional<Product> updatedProduct = productService.get(product.getId())
                .map(p -> productConverter.convert(product, p));

        if (updatedProduct.isPresent()) {
            productService.update(updatedProduct.get());
        } else {
            onCreated(product);
        }
    }

    @Override
    public void onDeleted(ProductDto product) {
        productService.get(product.getId())
                .map(p -> productConverter.convert(product, p))
                .ifPresent(productService::delete);
    }

}
