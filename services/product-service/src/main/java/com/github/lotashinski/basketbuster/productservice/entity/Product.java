package com.github.lotashinski.basketbuster.productservice.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "app_product")
public class Product {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    
    @Lob
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "collection_product_photos",
            joinColumns = {@JoinColumn(name = "product_id", nullable = false)}
    )
    @Column(name = "photo_href", nullable = false)
    private Set<String> photos = new HashSet<>();
    
    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<Category> categories = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<UserReview> reviews = new HashSet<>();
    
}
