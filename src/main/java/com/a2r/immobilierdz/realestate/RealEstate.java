package com.a2r.immobilierdz.realestate;


import com.a2r.immobilierdz.realestate.enums.Type;
import com.a2r.immobilierdz.house.Address;
import com.a2r.immobilierdz.picture.Picture;
import com.a2r.immobilierdz.rating.Rating;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RealEstate {

    @SequenceGenerator(
            name = "real_estate_sequence",
            sequenceName = "real_estate_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "real_estate_sequence")
    private Long id;
    private String name;
    private String description;
    private Boolean occupied;


    @OneToMany(mappedBy = "realEstate")
    private List<Picture> pictures;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Integer price;
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Address address;


    @OneToMany(mappedBy = "rating")
    @JsonBackReference
    private List<Rating> ratings;


    private Long ownerId;


    @Column(columnDefinition = "float default 5")
    private Float averageRating = 5f;



    public Float updateAverageRating(Float newRating){
        return (averageRating+newRating)/2;
    }

    public Float updateAlreadyExistingAverageRating(Float oldRating){
        return (averageRating*2)-oldRating;
    }

}
