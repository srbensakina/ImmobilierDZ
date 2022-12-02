package com.a2r.immobilierdz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
public class Rating {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JsonBackReference
    private House house;

    @OneToOne
    @JsonBackReference
    private User user;

    private Double rating;
}
