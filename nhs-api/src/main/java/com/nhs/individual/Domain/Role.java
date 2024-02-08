package com.nhs.individual.Domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(name = "role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 45)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JoinTable(name ="role_has_account",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Collection<Account> accounts;
}
