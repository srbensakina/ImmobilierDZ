package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.entity.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseLocationDTO {

    @NotBlank(message = "The name is required.")
    private String name;

    @NotBlank(message = "The description is required.")
    private String description;

    @NotNull(message = "The occupation state is required.")
    private Boolean occupied;

    @Positive
    @Min(value = 1, message = "Number of floors must be at least 1")
    @Max(value = 5, message = "Number of floors cannot exceed 100")
    private Integer numberOfFloors;

    private Type type;

    @NotNull(message = "The price is required.")
    @Positive
    private Integer price;

    @NotBlank(message="Wrong value for door number")
    private String doorNumber;

    @NotBlank(message = "The street name is required.")
    private String streetName;

    @NotBlank(message = "The street name is required.")
    private String city;


    private Float averageRating = 5f;

}
