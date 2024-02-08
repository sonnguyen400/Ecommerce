package com.nhs.individual.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user",cascade = {CascadeType.ALL})
    private Account account;

    @OneToMany(mappedBy = "user")
    private Collection<UserAddress> userAddresses;

}