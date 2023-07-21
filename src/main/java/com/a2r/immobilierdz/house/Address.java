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

    @Id
    @GeneratedValue
    private Long id;
    private String doorNumber;
    private String streetName;
    private String city;
    
    @OneToOne(mappedBy = "address")
    @JsonManagedReference
    private RealEstate realEstate;




}
