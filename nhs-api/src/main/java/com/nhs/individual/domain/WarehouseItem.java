package com.nhs.individual.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nhs.individual.domain.EmbeddedId.ProductItemInWarehouseId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_item_in_warehouse")
public class WarehouseItem {
    @EmbeddedId
    @JsonIgnore
    private ProductItemInWarehouseId id;

    @MapsId("productItemId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "product_item_id", nullable = false)
    @JsonIgnoreProperties("warehouses")
    private ProductItem productItem;

    @MapsId("warehouseId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "warehouse_id", nullable = false)
    @JsonBackReference
    private Warehouse warehouse;

    @Column(name = "SKU")
    private String SKU;
    @Column(name = "quantity")
    private Integer qty;


}