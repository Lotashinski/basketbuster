package com.github.lotashinski.basketbuster.productservice.service.crud.impl;

import com.github.lotashinski.basketbuster.productservice.dto.ProductCriteria;
import com.github.lotashinski.basketbuster.productservice.dto.ProductGetDto;
import com.github.lotashinski.basketbuster.productservice.dto.ProductItemDto;
import com.github.lotashinski.basketbuster.productservice.dto.ProductPostDto;
import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.service.converter.Converter;
import com.github.lotashinski.basketbuster.productservice.service.crud.ProductService;
import com.github.lotashinski.basketbuster.productservice.service.crud.exception.NotFoundException;
import com.github.lotashinski.basketbuster.productservice.service.jpa.JpaProductService;
import com.github.lotashinski.basketbuster.productservice.service.jpa.criteria.JpaProductCriteria;
import com.github.lotashinski.basketbuster.productservice.service.message.broker.ProductEventsSender;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public final class ProductServiceImpl implements ProductService {

    private final JpaProductService jpaProductService;

    private final Converter<Product, ProductGetDto> productToDto;

    private final Converter<ProductPostDto, Product> dtoToProduct;

    private final Converter<ProductCriteria, JpaProductCriteria> crtiteriaConverter;

    private final Converter<Product, ProductItemDto> productToItemDto;

    private final ProductEventsSender productEventsSender;


    @Override
    public Slice<ProductItemDto> get(ProductCriteria criteria) {
        JpaProductCriteria c = crtiteriaConverter.convert(criteria, new JpaProductCriteria());
        return jpaProductService.getByCriteria(c, PageRequestExtractor.extractPageRequest(criteria))
                .map(p -> productToItemDto.convert(p, new ProductItemDto()));
    }

    @Override
    public ProductGetDto get(Long id) {
        return jpaProductService.get(id)
                .map(p -> productToDto.convert(p, new ProductGetDto()))
                .orElseThrow(() -> createNotFoundException(id));
    }

    @Override
    public ProductGetDto create(ProductPostDto dto) {
        Product product = dtoToProduct.convert(dto, new Product());
        product = jpaProductService.create(product);

        productEventsSender.create(product);

        return productToDto.convert(product, new ProductGetDto());
    }

    @Override
    public ProductGetDto update(Long id, ProductPostDto dto) {
        Product product = jpaProductService.get(id)
                .map(p -> dtoToProduct.convert(dto, p))
                .map(jpaProductService::update)
                .orElseThrow(() -> createNotFoundException(id));

        productEventsSender.update(product);

        return productToDto.convert(product, new ProductGetDto());
    }

    @Override
    public void delete(Long id) {
        Product product = jpaProductService.get(id)
                .map(jpaProductService::delete)
                .orElseThrow(() -> createNotFoundException(id));

        productEventsSender.delete(product);
    }

    private static NotFoundException createNotFoundException(Long id) {
        return new NotFoundException(String.format("Product with id %s not found", id));
    }

}
