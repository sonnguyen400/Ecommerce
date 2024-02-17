package com.nhs.individual.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "variation_option")
public class VariationOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = {CascadeType.MERGE})
    @JoinColumn(name = "variation_id", nullable = false)
    @JsonBackReference
    private Variation variation;

    @Column(name = "value", length = 45)
    private String value;

}