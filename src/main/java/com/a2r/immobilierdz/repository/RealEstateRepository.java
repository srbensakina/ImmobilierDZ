package com.a2r.immobilierdz.repository;

import com.a2r.immobilierdz.entity.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
}
