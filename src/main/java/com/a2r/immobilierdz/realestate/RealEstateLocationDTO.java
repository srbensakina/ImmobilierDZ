package com.a2r.immobilierdz.realestate;


import com.a2r.immobilierdz.realestate.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RealEstateLocationDTO {

    @NotBlank(message = "The name is required.")
    private String name;

    @NotBlank(message = "The description is required.")
    private String description;

    @NotNull(message = "The occupation state is required.")
    private Boolean occupied;

    private Type type;

    @NotNull(message = "The price is required.")
    @Positive
    private Integer price;

    @NotBlank(message = "Wrong value for door number")
    private String doorNumber;

    @NotBlank(message = "The street name is required.")
    private String streetName;

    @NotBlank(message = "The street name is required.")
    private String city;

    @Positive
    @Min(value = 1, message = "Number of floors must be at least 1")
    @Max(value = 5, message = "Number of floors cannot exceed 100")
    private Float averageRating = 5f;


}
