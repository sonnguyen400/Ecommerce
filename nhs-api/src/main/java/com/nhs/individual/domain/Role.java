package com.nhs.individual.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 45)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.DETACH,mappedBy = "roles")
    @JsonIgnoreProperties("roles")
    private Collection<Account> accounts;
}
