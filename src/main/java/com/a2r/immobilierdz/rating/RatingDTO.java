package com.a2r.immobilierdz.rating;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingDTO {

    private Float rating;


    private Long realEstateId;
}
