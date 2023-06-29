package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.house.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {

List<Address> findByCity(String city);

}
