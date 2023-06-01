package com.a2r.immobilierdz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonBackReference
    private House house;


    //Check with ranga
    @OneToOne
    @JsonBackReference
    private Customer customer;

    private Double rating;
}
