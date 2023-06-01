package com.a2r.immobilierdz.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Flat extends RealEstate {

    private int floor;
    private String numberOfRooms;


}
