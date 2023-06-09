package com.a2r.immobilierdz.repository;

import com.a2r.immobilierdz.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer , Long> {
}
