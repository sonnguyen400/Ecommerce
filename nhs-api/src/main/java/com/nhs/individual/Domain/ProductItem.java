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
    private Product product_;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "product_item_options",
            joinColumns = @JoinColumn(name = "product_item_id"),
            inverseJoinColumns = @JoinColumn(name = "variation_option_id"))
    @JsonIgnoreProperties("productItems")
    private Collection<VariationOption> options;
    @Column(name = "product_image", length = 512)
    private String productImage;
    @Column(name = "price")
    @NotNull(message = "Price is required!")
    private BigDecimal price;
    @Column(name = "original_price")
    private BigDecimal originalPrice;
    @OneToMany(mappedBy = "productItem")
    @JsonIgnoreProperties("productItem")
    private List<WarehouseItem> warehouses;
}