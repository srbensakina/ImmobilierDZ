package com.a2r.immobilierdz.entity;


import com.a2r.immobilierdz.entity.enums.Type;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class House {


    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private Boolean occupied;
    private String photos;
    private Type type;
    @OneToOne
    @JsonBackReference
    private Address address;


    @OneToMany(mappedBy = "rating")
    @JsonBackReference
    private List<Rating> ratings;

    @ManyToOne
    @JsonBackReference
    private Owner owner;

}
