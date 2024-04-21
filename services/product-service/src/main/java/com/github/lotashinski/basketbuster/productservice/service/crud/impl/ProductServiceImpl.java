package com.github.lotashinski.basketbuster.productservice.service.crud.impl;

import com.github.lotashinski.basketbuster.productservice.dto.ProductCriteria;
import com.github.lotashinski.basketbuster.productservice.dto.ProductGetDto;
import com.github.lotashinski.basketbuster.productservice.dto.ProductPostDto;
import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.repository.CategoryRepository;
import com.github.lotashinski.basketbuster.productservice.repository.ProductRepository;
import com.github.lotashinski.basketbuster.productservice.service.crud.ProductService;
import com.github.lotashinski.basketbuster.productservice.service.crud.converter.Converter;
import com.github.lotashinski.basketbuster.productservice.service.crud.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public final class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final Converter<Product, ProductGetDto> productGetDtoConverter;

    private final Converter<ProductPostDto, Product> productPostDtoConverter;


    @Override
    public Slice<ProductGetDto> get(ProductCriteria criteria) {
        PageRequest pageRequest = PageRequestExtractor.extractPageRequest(criteria);
        Function<ProductCriteria, Slice<Product>> extractor;
        Long[] categoriesId = criteria.getCategories();

        if (categoriesId == null || categoriesId.length == 0) {
            extractor = c -> productRepository.findAll(pageRequest);
        } else {
            Set<Category> categories = getCategories(criteria);
            extractor = c -> productRepository.findByCategories(categories, pageRequest);
        }

        return extractor.apply(criteria)
                .map(p -> productGetDtoConverter.convert(p, new ProductGetDto()));
    }

    @Override
    public ProductGetDto get(Long id) {
        return productRepository.findById(id)
                .map(p -> productGetDtoConverter.convert(p, new ProductGetDto()))
                .orElseThrow(() -> createNotFoundException(id));
    }

    @Override
    public ProductGetDto create(ProductPostDto categoryDto) {
        Product product = productRepository.save(productPostDtoConverter.convert(categoryDto, new Product()));

        return productGetDtoConverter.convert(product, new ProductGetDto());
    }

    @Override
    public ProductGetDto update(Long id, ProductPostDto categoryDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private Set<Category> getCategories(ProductCriteria criteria) {
        return Arrays.stream(criteria.getCategories())
                .map(this::getCategory)
                .collect(Collectors.toSet());
    }

    private Category getCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        return category.orElseThrow(() -> new NotFoundException(String.format("Category with id %s not found", id)));
    }

    private static NotFoundException createNotFoundException(Long id) {
        return new NotFoundException(String.format("Product with id %s not found", id));
    }

}
