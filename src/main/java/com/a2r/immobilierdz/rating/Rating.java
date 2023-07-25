package com.a2r.immobilierdz.rating;

import com.a2r.immobilierdz.house.House;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"customerId", "house_id"}))
public class Rating {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonBackReference
    private House house;

    private Long customerId;

    private Float rating;
}
