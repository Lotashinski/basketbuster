package com.github.lotashinski.basketbuster.productservice.service.jpa;

import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.entity.UserReview;

import java.util.Collection;

public interface JpaProductUserReviewService {

    Collection<UserReview> getByProduct(Product product);

    Collection<UserReview> addToProduct(Product product, Collection<UserReview> reviews);

    Collection<UserReview> removeFromProduct(Product product, Collection<UserReview> reviews);

}
