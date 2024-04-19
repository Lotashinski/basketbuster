package com.github.lotashinski.basketbuster.storeservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
    
    @OneToMany(mappedBy = "pk.product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<BranchProduct> branchProducts = new HashSet<>();
    
}
