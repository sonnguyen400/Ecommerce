package com.nhs.individual.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nhs.individual.Constant.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "category_id",insertable = false,updatable = false)
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    @NotNull(message = "Category's identify is required")
    private Category category;


    @Column(name = "name", length = 45)
    @NotNull(message = "Product's name is required")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "picture", length = 512)
    private String picture;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnoreProperties("product_")
    private Collection<ProductItem> productItems;
    @Column(name = "status")
    ProductStatus status;

    @Column(name = "manufacturer", length =512)
    private String manufacturer;

}