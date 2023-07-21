package com.a2r.immobilierdz.flat;


import com.a2r.immobilierdz.realestate.RealEstate;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Flat extends RealEstate {

    private int floor;
    private String numberOfRooms;


}
