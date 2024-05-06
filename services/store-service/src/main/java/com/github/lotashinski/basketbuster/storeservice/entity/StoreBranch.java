package com.github.lotashinski.basketbuster.storeservice.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(
                name = "latitude", 
                column = @Column(name = "position_latitude")
        ),
        @AttributeOverride(
                name = "longitude", 
                column = @Column(name = "position_longitude")
        )
    })
    private Position position;
    
    @Column(name = "address", nullable = false)
    private String address;
    
    @Lob
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "rel_store_branch_schedule_item",
            joinColumns = {
                @JoinColumn(name = "store_branch_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "schedule_item_id", referencedColumnName = "id")
            }
    )
    private Map<Day, ScheduleItem> schedule;
    
    @OneToMany(mappedBy = "pk.storeBranch",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<BranchProduct> products = new HashSet<>();
    
}
