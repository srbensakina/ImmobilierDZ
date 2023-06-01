package com.a2r.immobilierdz.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;


@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class House extends RealEstate {

    private int numberOfFloors;


}
