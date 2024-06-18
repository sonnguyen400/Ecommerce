package com.nhs.individual.Domain;

import com.nhs.individual.constant.OrderStatus;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

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

    @Column(name = "shop_order_id",updatable = false,insertable = false)
    @Hidden
    private Integer shopOrderId;

    @Column(name = "update_at",columnDefinition = "Datetime default now()",insertable = false)
    private Date updateAt;
    @Column(name="detail")
    private String detail;
    @Column(name = "note")
    private String note;
    @Column(name = "status")
    private OrderStatus status;
}
