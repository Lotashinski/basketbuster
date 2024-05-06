package com.github.lotashinski.basketbuster.productservice.service.converter.impl;

import com.github.lotashinski.basketbuster.productservice.dto.UserReviewItemDto;
import com.github.lotashinski.basketbuster.productservice.entity.UserReview;
import com.github.lotashinski.basketbuster.productservice.service.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserReviewToUserItemReviewDtoConverter implements Converter<UserReview, UserReviewItemDto> {

    @Override
    public UserReviewItemDto convert(UserReview userReview, UserReviewItemDto userReviewItemDto) {
        userReviewItemDto.setId(userReview.getId());
        userReviewItemDto.setUsername(userReview.getUsername());
        userReviewItemDto.setStars(userReview.getStars());

        return userReviewItemDto;
    }

}
