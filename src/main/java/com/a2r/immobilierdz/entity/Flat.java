package com.a2r.immobilierdz.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Flat extends RealEstate {

    private int floor;
    private String numberOfRooms;


}
