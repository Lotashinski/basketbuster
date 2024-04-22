package com.github.lotashinski.basketbuster.productservice.service.crud.impl;

import com.github.lotashinski.basketbuster.productservice.dto.ProductCriteria;
import com.github.lotashinski.basketbuster.productservice.dto.ProductGetDto;
import com.github.lotashinski.basketbuster.productservice.dto.ProductPostDto;
import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.service.crud.ProductService;
import com.github.lotashinski.basketbuster.productservice.service.crud.converter.Converter;
import com.github.lotashinski.basketbuster.productservice.service.crud.exception.NotFoundException;
import com.github.lotashinski.basketbuster.productservice.service.jpa.JpaProductService;
import com.github.lotashinski.basketbuster.productservice.service.jpa.criteria.JpaProductCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final JpaProductService jpaProductService;

    private final Converter<Product, ProductGetDto> productToDto;

    private final Converter<ProductPostDto, Product> dtoToProduct;

    private final Converter<ProductCriteria, JpaProductCriteria> crtiteriaConverter;


    @Override
    public Slice<ProductGetDto> get(ProductCriteria criteria) {
        JpaProductCriteria c = crtiteriaConverter.convert(criteria, new JpaProductCriteria());
        return jpaProductService.getByCriteria(c, PageRequestExtractor.extractPageRequest(criteria))
                .map(p -> productToDto.convert(p, new ProductGetDto()));
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

        return productToDto.convert(product, new ProductGetDto());
    }

    @Override
    public ProductGetDto update(Long id, ProductPostDto dto) {
        return jpaProductService.get(id)
                .map(p -> dtoToProduct.convert(dto, p))
                .map(jpaProductService::update)
                .map(p -> productToDto.convert(p, new ProductGetDto()))
                .orElseThrow(() -> createNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        jpaProductService.get(id)
                .map(jpaProductService::delete)
                .orElseThrow(() -> createNotFoundException(id));
    }

    private static NotFoundException createNotFoundException(Long id) {
        return new NotFoundException(String.format("Product with id %s not found", id));
    }
}
