package com.github.lotashinski.basketbuster.productservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "app_user_review")
public class UserReview {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "start", nullable = false)
    private Integer stars;

}
