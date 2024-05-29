package com.nhs.individual.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "shop_order")
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @NotNull(message = "User and its id is required")
    private User user;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @NotNull(message = "Address Information is required")
    private Address address;

    @Column(name = "address_id",insertable = false,updatable = false)

    private Integer addressId;

    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "order_date",insertable = false,columnDefinition = "DATETIME default now()")
    private Instant orderDate;

    @Column(name = "total",scale = 9, precision = 18)
    @Min(value = 1,message = "Total value can not be negative or equal to 0")
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_method")
    @NotNull(message = "Shipping method information is required")
    private ShippingMethod shippingMethod;



    @Lob
    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    private Collection<ShopOrderStatus> status;

    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    private Collection<OrderLine> orderLines;

}