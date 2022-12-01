package com.a2r.immobilierdz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String doorNumber;
    private String streetName;
    private String city;
    @OneToOne(mappedBy = "address")
    @JsonManagedReference
    private House house;
}
