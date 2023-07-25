package com.a2r.immobilierdz.rating;

import com.a2r.immobilierdz.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating , Long> {

   Optional<Rating> findByHouseIdAndCustomerId(Long houseId ,Long customerId);

   boolean existsByCustomerIdAndHouse_Id(Long userId , Long houseId);
}
