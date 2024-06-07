package com.nhs.individual.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.time.Instant;
import java.util.Collection;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "firstname", length = 45)
    @Length(min=1,max = 45,message = "First name is between 1 and 45 characters in length")
    private String firstname;

    @Column(name = "lastname", length = 45)
    @Length(min=1,max = 45,message = "Last name is between 1 and 45 characters in length")

    private String lastname;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "phone_number", length = 12)
    private String phoneNumber;

    @Column(name = "email")
    private String email;


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<UserAddress> userAddresses;


}