package com.nhs.individual.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collection;

@Data
@Entity
@Table(name = "product_item")

public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("productItems")
    private Product product_;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "product_item_options",
    joinColumns = @JoinColumn(name = "product_item_id"),
    inverseJoinColumns = @JoinColumn(name = "variation_option_id"))
    @JsonIgnoreProperties("productItems")
    private Collection<VariationOption> options;

    @Column(name = "product_image", length = 512)
    private String productImage;

    @Column(name = "price", precision = 2)
    private BigDecimal price;
}