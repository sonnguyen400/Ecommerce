package com.nhs.individual.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_item_options", schema = "ecommerce")
public class ProductItemOption {
    @EmbeddedId
    private ProductItemOptionId id;

    @MapsId("variationOptionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "variation_option_id", nullable = false)
    private VariationOption variationOption;

    @MapsId("productItemId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_item_id", nullable = false)
    private ProductItem productItem;

}