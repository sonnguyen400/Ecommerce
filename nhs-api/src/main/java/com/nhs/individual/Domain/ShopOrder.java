package com.nhs.individual.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "shop_order")
public class ShopOrder implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull(message = "User Information is required")
    private User user;

    @Column(name = "user_id",insertable = false,updatable = false)
    @Hidden
    private Integer userId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    @NotNull(message = "Address Information is required")
    private Address address;

    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "order_date",insertable = false,columnDefinition = "DATETIME default now()")
    private Date orderDate;

    @Column(name = "total",scale = 2, precision = 18)
    @Min(value = 1,message = "Total value can not be negative or equal to 0")
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_method")
    private ShippingMethod shippingMethod;

    @Lob
    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    private Collection<ShopOrderStatus> status;

    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"order","hibernateLazyInitializer", "handler"})
    private Collection<OrderLine> orderLines;

}