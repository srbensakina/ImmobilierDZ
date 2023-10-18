package com.a2r.immobilierdz.realestate;


import com.a2r.immobilierdz.realestate.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RealEstateLocationDTO {

    private Long id;

    @NotBlank(message = "The name is required.")
    private String name;

    @NotBlank(message = "The description is required.")
    private String description;

    @NotNull(message = "The occupation state is required.")
    private Boolean occupied;

    @NotNull
    // @EnumValidator(enumClass = Type.class, message = "The type must either be RENT or COLLOCATION or SALE")
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

    private Float averageRating = 0f;


    private String mainPicture;
 //   private List<String> picturesList;


}
