package com.a2r.immobilierdz.rating;

import com.a2r.immobilierdz.config.MQConfig;
import com.a2r.immobilierdz.rating.RatingDTO;
import com.a2r.immobilierdz.house.House;
import com.a2r.immobilierdz.rating.Rating;
import com.a2r.immobilierdz.rating.RatingMapper;
import com.a2r.immobilierdz.house.HouseRepository;
import com.a2r.immobilierdz.rating.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final HouseRepository houseRepository;
    private final RatingMapper ratingMapper;
    private final RabbitTemplate rabbitTemplate;


    @Transactional
    @RabbitListener(queues = MQConfig.QUEUE)
    public void processRating(RatingDTO ratingDTO) {
        House house = houseRepository.findById(ratingDTO.getHouseId()).orElseThrow(NoSuchElementException::new);
        if (house != null) {
            house.setAverageRating(house.updateAverageRating(ratingDTO.getRating()));
            houseRepository.save(house);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @Transactional
    public Rating insertRating(RatingDTO ratingDTO, Jwt principal) {
        System.out.println("ratinnn"+ ratingDTO);
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, ratingDTO);
        return ratingRepository.save(setUserId(ratingDTO,principal));
    }

    @PreAuthorize("isAuthenticated()")
    public Rating getRating(Long houseId, Jwt principal) {
        return ratingRepository.findByHouseIdAndCustomerId(houseId, principal.getSubject()).orElseThrow(NoSuchElementException::new);
    }

    @PreAuthorize("isAuthenticated()")
    public Rating updateRating(RatingDTO ratingDTO) {
        return ratingRepository.save(ratingMapper.map(ratingDTO));
    }

    public Rating setUserId(RatingDTO ratingDTO, Jwt principal){
        Rating rating = ratingMapper.map(ratingDTO);
        rating.setCustomerId(principal.getSubject());
        return rating;
    }
}
