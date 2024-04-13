package com.nhs.individual.Domain;

import com.nhs.individual.Domain.EmbeddedId.ShopOrderStatusId;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "shop_order_status")
public class ShopOrderStatus {
    @EmbeddedId
    private ShopOrderStatusId id;
    @MapsId("shopOrderId")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "shop_order_id")
    private ShopOrder order;

    @MapsId("orderStatusId")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;
    @Column(name = "update_at",columnDefinition = "Datetime default now()",insertable = false)
    private Instant updateAt;
    @Column(name="detail")
    private String detail;
    @Column(name = "note")
    private String note;
}
