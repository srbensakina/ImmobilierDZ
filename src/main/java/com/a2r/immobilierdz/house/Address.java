package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.realestate.RealEstate;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"doorNumber", "streetName", "city"}))

public class Address {

    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "address_sequence")
    private Long id;
    private String doorNumber;
    private String streetName;
    private String city;
    
    @OneToOne(mappedBy = "address")
    @JsonManagedReference
    private RealEstate realEstate;




}
