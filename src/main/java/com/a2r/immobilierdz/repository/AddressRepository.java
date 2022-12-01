package com.a2r.immobilierdz.repository;

import com.a2r.immobilierdz.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
