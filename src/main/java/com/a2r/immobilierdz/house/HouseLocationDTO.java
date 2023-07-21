package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.realestate.RealEstateLocationDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class HouseLocationDTO extends RealEstateLocationDTO {

    @Positive
    @Min(value = 1, message = "Number of floors must be at least 1")
    @Max(value = 5, message = "Number of floors cannot exceed 100")
    private Integer numberOfFloors;
}
