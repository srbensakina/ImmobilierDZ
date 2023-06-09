package com.a2r.immobilierdz.entity;


import com.a2r.immobilierdz.entity.enums.Type;
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


    @Id
    @GeneratedValue
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

  /*  @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Owner owner;*/

    private String ownerId;


    //@Column(columnDefinition = "float default 5")
    private Float averageRating = 5f;



    public Float updateAverageRating(Float newRating){
        return (averageRating+newRating)/2;
    }

}
