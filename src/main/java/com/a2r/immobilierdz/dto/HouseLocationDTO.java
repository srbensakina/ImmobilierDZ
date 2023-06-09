package com.a2r.immobilierdz.dto;

import com.a2r.immobilierdz.entity.enums.Type;
import lombok.Data;

@Data
public class HouseLocationDTO {

    private String name;
    private String description;
    private Boolean occupied;
    private Integer numberOfFloors;
    private Type type;
    private Integer price;
    private String doorNumber;
    private String streetName;
    private String city;
    private String ownerId;
    private Float averageRating = 5f;

}
