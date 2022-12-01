package com.a2r.immobilierdz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @OneToOne
    @JsonBackReference
    private House houseId;
    @OneToOne
    @JsonBackReference
    private User userId;
    private Double rating;
}
