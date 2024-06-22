package com.nhs.individual.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "shop_order_payment")
public class ShopOrderPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false,cascade = CascadeType.MERGE)
    @JoinColumn(name = "payment_id", nullable = true)
    private Payment type;

    @OneToOne(mappedBy = "payment", optional = false)
    @JsonIgnore
    private ShopOrder order;

    @Size(max = 45)
    @Column(name = "order_number", length = 45)
    private String orderNumber;

    @ColumnDefault("current_timestamp()")
    @Column(name = "update_at")
    private Instant updateAt;

    @Lob
    @Column(name = "status")
    private String status;

}