package com.github.lotashinski.basketbuster.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "app_user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;
    
    @OneToMany(mappedBy = "user")
    private Set<UserReview> reviews = new HashSet<>();
    
}
