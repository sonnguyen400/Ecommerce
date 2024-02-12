package com.nhs.individual.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "phone_number", length = 12)
    @NotEmpty
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "user",cascade = {CascadeType.PERSIST})
    private Account account;

    @OneToMany(mappedBy = "user")
    private Collection<UserAddress> userAddresses;

}