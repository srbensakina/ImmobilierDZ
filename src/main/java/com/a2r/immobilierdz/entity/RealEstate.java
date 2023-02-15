package com.a2r.immobilierdz.entity;


import com.a2r.immobilierdz.entity.enums.Type;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.TABLE;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
//@org.hibernate.annotations.Entity(polymorphism = PolymorphismType.EXPLICIT)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy = InheritanceType.JOINED)
public class RealEstate {


    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private Boolean occupied;
    private String photos;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Integer price;
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Address address;


    @OneToMany(mappedBy = "rating")
    @JsonBackReference
    private List<Rating> ratings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Owner owner;

}
