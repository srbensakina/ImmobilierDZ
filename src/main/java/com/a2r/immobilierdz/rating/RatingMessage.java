package com.a2r.immobilierdz.rating;


import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RatingMessage {

    private Float rating;
    private Long realEstateId;
    private Long userId;
    private boolean updated = false;
    private Float oldRating;

}
