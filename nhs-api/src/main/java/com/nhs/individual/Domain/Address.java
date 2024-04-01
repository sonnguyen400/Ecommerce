package com.nhs.individual.Domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "building", length = 45)
    private String building;

    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Column(name = "city", length = 45)
    private String city;

    @Column(name = "region", length = 45)
    private String region;

    @Column(name = "business_address")
    private Byte businessAddress;

    @Column(name = "address_line_1", length = 45)
    private String addressLine1;

    @Column(name = "address_line_2", length = 45)
    private String addressLine2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

}