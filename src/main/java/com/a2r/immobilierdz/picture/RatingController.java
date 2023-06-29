package com.a2r.immobilierdz.picture;


import com.a2r.immobilierdz.rating.RatingDTO;
import com.a2r.immobilierdz.rating.Rating;
import com.a2r.immobilierdz.rating.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public Rating insertRating(@RequestBody RatingDTO ratingDTO, @AuthenticationPrincipal Jwt principal) {
     return ratingService.insertRating(ratingDTO, principal);
    }

    @GetMapping("{houseId}")
    Rating getRating(@PathVariable("houseId") Long houseId, @AuthenticationPrincipal Jwt principal){
        return ratingService.getRating(houseId, principal);
    }

    @PutMapping
    public Rating updateRating(@RequestBody RatingDTO ratingDTO) {
        return ratingService.updateRating(ratingDTO);
    }

}
