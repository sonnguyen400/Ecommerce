package com.nhs.individual.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProductItemOptionId implements Serializable {
    private static final long serialVersionUID = 8417545720557870354L;
    @NotNull
    @Column(name = "variation_option_id", nullable = false)
    private Integer variationOptionId;

    @NotNull
    @Column(name = "product_item_id", nullable = false)
    private Integer productItemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductItemOptionId entity = (ProductItemOptionId) o;
        return Objects.equals(this.variationOptionId, entity.variationOptionId) &&
                Objects.equals(this.productItemId, entity.productItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variationOptionId, productItemId);
    }

}