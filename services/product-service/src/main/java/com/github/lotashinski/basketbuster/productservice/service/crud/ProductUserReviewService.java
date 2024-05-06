package com.github.lotashinski.basketbuster.productservice.service.crud;

import com.github.lotashinski.basketbuster.productservice.dto.UserReviewItemDto;

import java.util.Collection;

public interface ProductUserReviewService {

    Collection<UserReviewItemDto> getUserReviews(Long productId);

}
