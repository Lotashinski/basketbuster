package com.github.lotashinski.basketbuster.productservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "app_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = true)
    private Category owner;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<Category> children = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rel_category_product",
            joinColumns = {@JoinColumn(
                    name = "category_id",
                    nullable = false
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "product_id",
                    nullable = false
            )}
    )
    private Set<Product> products = new HashSet<>();

}
