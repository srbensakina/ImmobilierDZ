package com.a2r.immobilierdz.house.specs;

import com.a2r.immobilierdz.realestate.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
}
