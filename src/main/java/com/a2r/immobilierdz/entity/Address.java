package com.a2r.immobilierdz.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String doorNumber;
    private String streetName;
    private String city;
    @OneToOne(mappedBy = "address" , fetch = FetchType.LAZY)
    @JsonManagedReference
    private House house;


}
