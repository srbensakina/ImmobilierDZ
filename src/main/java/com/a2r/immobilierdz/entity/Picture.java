package com.a2r.immobilierdz.entity;


import com.a2r.immobilierdz.entity.RealEstate;
import lombok.*;

import javax.persistence.*;

@Data
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

    @ManyToOne
   private RealEstate realEstate;

}
