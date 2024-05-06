package com.github.lotashinski.basketbuster.productservice.service.crud.impl;

import com.github.lotashinski.basketbuster.productservice.dto.UserReviewItemDto;
import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.entity.UserReview;
import com.github.lotashinski.basketbuster.productservice.service.converter.Converter;
import com.github.lotashinski.basketbuster.productservice.service.crud.ProductUserReviewService;
import com.github.lotashinski.basketbuster.productservice.service.crud.exception.NotFoundException;
import com.github.lotashinski.basketbuster.productservice.service.jpa.JpaProductService;
import com.github.lotashinski.basketbuster.productservice.service.jpa.JpaProductUserReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class ProductUserReviewServiceImpl implements ProductUserReviewService {

    private final JpaProductService productService;

    private final JpaProductUserReviewService productUserReviewService;

    private final Converter<UserReview, UserReviewItemDto> converter;


    @Override
    public Collection<UserReviewItemDto> getUserReviews(Long productId) {
        Product product = productService.get(productId)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", productId)));

        return productUserReviewService.getByProduct(product)
                .stream()
                .map(ur -> converter.convert(ur, new UserReviewItemDto()))
                .toList();
    }
}
