package com.a2r.immobilierdz.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;


@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class House extends RealEstate {

    private Integer numberOfFloors;


}
