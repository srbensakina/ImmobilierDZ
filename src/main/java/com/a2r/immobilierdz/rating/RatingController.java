package com.a2r.immobilierdz.rating;


import com.a2r.immobilierdz.rating.RatingDTO;
import com.a2r.immobilierdz.rating.Rating;
import com.a2r.immobilierdz.rating.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/ratings")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<?> insertRating(@Valid @RequestBody RatingDTO ratingDTO, @AuthenticationPrincipal String principal) {
        if (ratingDTO.getRating() == null || ratingDTO.getRating() < 1 || ratingDTO.getRating() > 5) {
            return ResponseEntity.badRequest().body("Rating must be between 1 and 5.");
        }
        if (ratingDTO.getRealEstateId() == null || ratingDTO.getRealEstateId() <= 0) {
            return ResponseEntity.badRequest().body("Real estate ID must be a positive value.");
        }
        try {
          Rating rating =  ratingService.insertRating(ratingDTO, principal);
            return ResponseEntity.ok(rating);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("User already has a rating.");
       } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process rating.");
        }
    }

    @GetMapping("{houseId}")
   ResponseEntity<Rating> getRating(@PathVariable("houseId") Long houseId, @AuthenticationPrincipal String principal){
        Rating rating = ratingService.getRating(houseId, principal);
        if (rating != null) {
            return ResponseEntity.ok(rating);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping()
    public ResponseEntity<?> updateRating(@Valid @RequestBody RatingDTO ratingDTO , @AuthenticationPrincipal String principal) {

        if (ratingDTO.getRating() == null || ratingDTO.getRating() < 1 || ratingDTO.getRating() > 5) {
            return ResponseEntity.badRequest().body("Rating must be between 1 and 5.");
        }

        if (ratingDTO.getRealEstateId() == null || ratingDTO.getRealEstateId() <= 0) {
            return ResponseEntity.badRequest().body("Real estate ID must be a positive value.");
        }
        try {
            Rating updatedRating = ratingService.updateRating(ratingDTO, principal);
            if (updatedRating != null) {
                return ResponseEntity.ok(updatedRating);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
