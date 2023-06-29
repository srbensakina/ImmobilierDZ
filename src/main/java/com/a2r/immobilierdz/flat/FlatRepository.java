package com.a2r.immobilierdz.flat;

import com.a2r.immobilierdz.flat.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FlatRepository extends JpaRepository<Flat , Long> {
}
