package com.nhs.individual.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
    private Category category;


    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "product_image", length = 512)
    private String productImage;

    @OneToMany(mappedBy = "product_",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnoreProperties("product_")
    private Collection<ProductItem> productItems;


}