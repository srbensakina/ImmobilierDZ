package com.a2r.immobilierdz.repository;

import com.a2r.immobilierdz.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HouseRepository extends JpaRepository<House , Long> {

    List<House>findAllByAddress_City(String city);

}
