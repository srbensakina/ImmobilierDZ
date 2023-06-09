package com.a2r.immobilierdz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;

    @OneToMany(mappedBy = "rating")
    @JsonBackReference
    private List<Rating> ratings;

}
