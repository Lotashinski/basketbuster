package com.github.lotashinski.basketbuster.productservice.repository;

import com.github.lotashinski.basketbuster.productservice.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}
