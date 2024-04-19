package com.github.lotashinski.basketbuster.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    
}
