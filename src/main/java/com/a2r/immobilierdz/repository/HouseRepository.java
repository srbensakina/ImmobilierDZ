package com.a2r.immobilierdz.repository;

import com.a2r.immobilierdz.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HouseRepository extends JpaRepository<House , Long> {


}
