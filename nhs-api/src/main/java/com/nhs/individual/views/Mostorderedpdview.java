package com.nhs.individual.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "mostorderedpdview")
public class Mostorderedpdview {
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryId;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 255)
    @Column(name = "picture")
    private String picture;

    @Size(max = 512)
    @Column(name = "manufacturer", length = 512)
    private String manufacturer;

    @Column(name = "status")
    private Byte status;

    @Column(name = "total", precision = 42)
    private BigDecimal total;

    @NotNull
    @Column(name = "product_id", nullable = false)
    private Integer productId;

}