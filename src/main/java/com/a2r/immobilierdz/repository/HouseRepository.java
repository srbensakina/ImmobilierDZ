package com.a2r.immobilierdz.repository;

import com.a2r.immobilierdz.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {

    List<House> findAllByAddress_City(String city);

    List<House> findAllByOwnerId(String id);

}
