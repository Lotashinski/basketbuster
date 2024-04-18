package com.github.lotashinski.basketbuster.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "app_order")
public class Order {
    
    public static enum Status {
        CANCELLED,
        IN_PROCESSIONG,
        PROCESSED,
        DELIVERED,
        AT_THE_POINT,
        COMPLETED,    
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "created_at", nullable = false)
    private LocalTime createdAt;
    
    @Column(name = "finished_at")
    private LocalTime finishedAt;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.IN_PROCESSIONG;
    
    @Column(name = "is_pickup", nullable = false)
    private Boolean isPickup;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "address", nullable = false)
    private String address;
    
    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;
    
    @ManyToOne
    @JoinColumn(name = "store_branch_id", nullable = false)
    private StoreBranch storeBranch;
    
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private Set<OrderItem> items = new HashSet<>();
    
    public BigDecimal getCost() {
        return getItems()
                .stream()
                .map(OrderItem::getCost)
                .reduce(BigDecimal.ZERO, (l, r) -> l.add(r));
    }
    
}
