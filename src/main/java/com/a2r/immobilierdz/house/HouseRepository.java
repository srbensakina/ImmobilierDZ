package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.house.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {

    List<House> findAllByAddress_City(String city);

    Optional<House> findByName(String name);
}
