package com.a2r.immobilierdz.repository;

import com.a2r.immobilierdz.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FlatRepository extends JpaRepository<Flat , Long> {
}
