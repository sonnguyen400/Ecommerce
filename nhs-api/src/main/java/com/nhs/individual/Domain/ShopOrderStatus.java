package com.nhs.individual.Domain;

import com.nhs.individual.Constant.OrderStatus;
import com.nhs.individual.Domain.EmbeddedId.ShopOrderStatusId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "shop_order_status")
@Getter
@Setter
public class ShopOrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "shop_order_id")
    private ShopOrder order;

    @Column(name = "update_at",columnDefinition = "Datetime default now()",insertable = false)
    private Instant updateAt;
    @Column(name="detail")
    private String detail;
    @Column(name = "note")
    private String note;
    @Column(name = "status")
    private OrderStatus status;
}
