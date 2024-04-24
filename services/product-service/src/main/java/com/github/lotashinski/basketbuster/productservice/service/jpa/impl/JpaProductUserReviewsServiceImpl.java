package com.github.lotashinski.basketbuster.productservice.service.jpa.impl;

import com.github.lotashinski.basketbuster.productservice.entity.Product;
import com.github.lotashinski.basketbuster.productservice.entity.UserReview;
import com.github.lotashinski.basketbuster.productservice.service.jpa.JpaProductUserReviewService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class JpaProductUserReviewsServiceImpl implements JpaProductUserReviewService {

    private final EntityManager entityManager;


    @Override
    public Collection<UserReview> getByProduct(Product product) {
        return product.getReviews();
    }

    @Transactional
    @Override
    public Collection<UserReview> addToProduct(Product product, Collection<UserReview> reviews) {
        product.getReviews().addAll(reviews);

        reviews.forEach(review -> review.setProduct(product));
        reviews.forEach(entityManager::persist);

        entityManager.flush();


        return reviews;
    }

    @Transactional
    @Override
    public Collection<UserReview> removeFromProduct(Product product, Collection<UserReview> reviews) {
        reviews.retainAll(product.getReviews());
        product.getReviews().removeAll(reviews);

        entityManager.flush();

        return reviews;
    }

}
