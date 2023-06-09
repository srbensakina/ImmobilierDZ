package com.a2r.immobilierdz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Rating {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonBackReference
    private House house;


    private String customerId;


    private Float rating;
}
