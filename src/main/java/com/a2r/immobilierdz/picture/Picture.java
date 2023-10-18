package com.a2r.immobilierdz.picture;


import com.a2r.immobilierdz.house.House;
import com.a2r.immobilierdz.realestate.RealEstate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

//@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Picture {

    @Id
    @GeneratedValue
   private Long id;
    private String name;
    private String filePath;

  /* @ManyToOne(fetch = FetchType.LAZY)
    private House house;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    //@JoinColumn(name = "real_estate_id")
    private RealEstate realEstate;

}
