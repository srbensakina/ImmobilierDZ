package com.a2r.immobilierdz.repository;

import com.a2r.immobilierdz.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    Optional<Picture> findByName(String name);

    List<Picture>findAllByRealEstateId(Long id);
}
