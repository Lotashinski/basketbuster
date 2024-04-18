package com.github.lotashinski.basketbuster.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "app_store_branch")
public class StoreBranch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "url_branch", nullable = false)
    private String urlBranch;
    
    @Column(name = "url_store", nullable = false)
    private String urlStore;
    
    @Column(name = "store_title", nullable = false)
    private String storeTitle;
    
}
