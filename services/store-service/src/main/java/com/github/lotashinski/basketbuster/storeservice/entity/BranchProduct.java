package com.github.lotashinski.basketbuster.storeservice.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "app_branch_product")
public class BranchProduct {

    @Getter
    @Setter
    @Embeddable
    @EqualsAndHashCode
    public static class BranchProductPk {

        @ManyToOne
        private Product product;

        @ManyToOne
        private StoreBranch storeBranch;

    }

    
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(
                name = "product",
                column = @Column(name = "product_id", nullable = false)
        ),
        @AttributeOverride(
                name = "storeBranch",
                column = @Column(name = "store_branch_id", nullable = false)
        )
    })
    private BranchProductPk pk = new BranchProductPk();

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    public Product getProduct() {
        return pk.getProduct();
    }

    public StoreBranch getStoreBranch() {
        return pk.getStoreBranch();
    }

    public void setProduct(Product product) {
        pk.setProduct(product);
    }

    public void setStoreBranch(StoreBranch storeBranch) {
        pk.setStoreBranch(storeBranch);
    }

}
