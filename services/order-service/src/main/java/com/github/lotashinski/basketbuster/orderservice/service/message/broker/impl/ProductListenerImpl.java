package com.github.lotashinski.basketbuster.orderservice.service.message.broker.impl;

import com.github.lotashinski.basketbuster.orderservice.entity.Product;
import com.github.lotashinski.basketbuster.orderservice.service.converter.Converter;
import com.github.lotashinski.basketbuster.orderservice.service.jpa.JpaProductService;
import com.github.lotashinski.basketbuster.orderservice.service.message.broker.ProductListener;
import com.github.lotashinski.basketbuster.orderservice.service.message.broker.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductListenerImpl implements ProductListener {

    private final Converter<ProductDto, Product> productConverter;

    private final JpaProductService productService;


    @Override
    public void onCreated(ProductDto product) {
        log.info("Try to create product: {}", product);
        Product newProduct = productConverter.convert(product, new Product());
        productService.create(newProduct);
        log.info("Product created: {}", product);
    }

    @Override
    public void onUpdated(ProductDto product) {
        log.info("Try to update product: {}", product);
        Optional<Product> updatedProduct = productService.get(product.getId())
                .map(p -> productConverter.convert(product, p));

        if (updatedProduct.isPresent()) {
            productService.update(updatedProduct.get());
            log.info("Product updated: {}", product);
        } else {
            log.info("Product not updated: {}.", product);
            onCreated(product);
        };
    }

    @Override
    public void onDeleted(ProductDto product) {
        log.info("Try to delete product: {}", product);
        productService.get(product.getId())
                .map(p -> productConverter.convert(product, p))
                .map(productService::delete)
                .ifPresent(p -> log.info("Product deleted: {}", product));
    }

}
