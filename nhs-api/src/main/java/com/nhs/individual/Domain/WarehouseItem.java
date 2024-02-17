package com.nhs.individual.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_item_in_warehouse")
public class WarehouseItem {
    @EmbeddedId
    private ProductItemInWarehouseId id;

    @MapsId("productItemId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_item_id", nullable = false)
    private ProductItem productItem;

    @MapsId("warehouseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @Column(name = "SKU")
    private String SKU;
    @Column(name = "quantity")
    private Integer quantity;


}