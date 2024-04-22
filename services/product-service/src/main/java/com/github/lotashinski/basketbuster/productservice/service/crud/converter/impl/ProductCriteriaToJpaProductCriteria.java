package com.github.lotashinski.basketbuster.productservice.service.crud.converter.impl;

import com.github.lotashinski.basketbuster.productservice.dto.ProductCriteria;
import com.github.lotashinski.basketbuster.productservice.entity.Category;
import com.github.lotashinski.basketbuster.productservice.repository.CategoryRepository;
import com.github.lotashinski.basketbuster.productservice.service.crud.converter.Converter;
import com.github.lotashinski.basketbuster.productservice.service.jpa.criteria.JpaProductCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public final class ProductCriteriaToJpaProductCriteria implements Converter<ProductCriteria, JpaProductCriteria> {

    private final CategoryRepository categoryRepository;


    @Override
    public JpaProductCriteria convert(ProductCriteria productCriteria, JpaProductCriteria jpaProductCriteria) {
        Long[] categoriesId = productCriteria.getCategories();
        String title = productCriteria.getTitle();

        if (categoriesId != null && categoriesId.length > 0) {
            jpaProductCriteria.setCategories(extractCategories(categoriesId));
        }

        if (title != null && !title.isEmpty()) {
            jpaProductCriteria.setTitle(title);
        }

        return jpaProductCriteria;
    }

    private List<Category> extractCategories(Long[] categoriesId) {
        List<Long> ids = Arrays.stream(categoriesId).toList();
        return categoryRepository.findAllById(ids);
    }

}
