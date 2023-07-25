package com.a2r.immobilierdz.rating;

import com.a2r.immobilierdz.config.MQConfig;
import com.a2r.immobilierdz.exceptions.NoSuchRatingException;
import com.a2r.immobilierdz.exceptions.RealEstateNotFoundException;
import com.a2r.immobilierdz.house.House;
import com.a2r.immobilierdz.house.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final HouseRepository houseRepository;
    private final RatingMapper ratingMapper;
    private final RabbitTemplate rabbitTemplate;


    @Transactional
    @RabbitListener(queues = MQConfig.QUEUE)
    public void processRating(RatingMessage ratingMessage) {
            House house = houseRepository.findById(ratingMessage.getRealEstateId()).orElseThrow(() -> new RealEstateNotFoundException("This real estate doesn't exist"));
            if (ratingMessage.isUpdated()) {
                house.setAverageRating(house.updateAlreadyExistingAverageRating(ratingMessage.getOldRating()));
            }
            if (house != null) {
                house.setAverageRating(house.updateAverageRating(ratingMessage.getRating()));
                houseRepository.save(house);
            }


    }
    @Transactional
    public Rating insertRating(RatingDTO ratingDTO, String principal) {
        if (ratingRepository.existsByCustomerIdAndHouse_Id(Long.valueOf(principal), ratingDTO.getRealEstateId())) {
            throw new IllegalArgumentException();
        }
        RatingMessage message = RatingMessage.builder().userId(Long.valueOf(principal)).realEstateId(ratingDTO.getRealEstateId()).rating(ratingDTO.getRating()).build();
            rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, message);
            return ratingRepository.save(setUserId(ratingDTO, principal));
        }


    public Rating getRating(Long houseId, String principal) {
        return ratingRepository.findByHouseIdAndCustomerId(houseId, Long.valueOf(principal)).orElseThrow(() -> new NoSuchRatingException("This rating doesn't exist"));
    }

    @Transactional
    public Rating updateRating(RatingDTO ratingDTO, String principal) {
        Rating existingRating = getRating(ratingDTO.getRealEstateId(), principal);
        RatingMessage message = RatingMessage.builder().userId(Long.valueOf(principal)).realEstateId(ratingDTO.getRealEstateId()).rating(ratingDTO.getRating()).updated(true).oldRating(existingRating.getRating()).build();
        existingRating.setRating(ratingDTO.getRating());
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, message);
        return ratingRepository.save(existingRating);
    }

    public Rating setUserId(RatingDTO ratingDTO, String principal) {
        Rating rating = ratingMapper.map(ratingDTO);
        rating.setCustomerId(Long.valueOf(principal));
        return rating;
    }
}
