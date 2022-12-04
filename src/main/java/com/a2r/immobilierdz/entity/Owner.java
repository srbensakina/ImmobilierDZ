package com.a2r.immobilierdz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class Owner {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String phone;


    @OneToMany(mappedBy = "owner")
    @JsonBackReference
    private List<House> houses;
}