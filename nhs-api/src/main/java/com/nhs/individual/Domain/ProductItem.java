package com.nhs.individual.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "product_item")
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("productItems")
    @NotNull(message = "Product Information is required")
    private Product product;

    @Column(name = "product_id", insertable = false,updatable = false)
    private Integer productId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "product_item_options",
            joinColumns = @JoinColumn(name = "product_item_id"),
            inverseJoinColumns = @JoinColumn(name = "variation_option_id"))
    @JsonIgnoreProperties("productItems")
    private Collection<VariationOption> options;

    @Column(name = "picture", length = 512)
    private String picture;

    @Column(name = "price",nullable = true)
    private BigDecimal price;

    @Column(name = "original_price")
    @NotNull(message = "ORIGINAL is required!")
    private BigDecimal originalPrice;

    @OneToMany(mappedBy = "productItem")
    @JsonIgnoreProperties("productItem")
    private List<WarehouseItem> warehouses;
}