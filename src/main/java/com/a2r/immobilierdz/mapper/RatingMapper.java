package com.a2r.immobilierdz.mapper;

import com.a2r.immobilierdz.dto.HouseLocationDTO;
import com.a2r.immobilierdz.dto.RatingDTO;
import com.a2r.immobilierdz.entity.House;
import com.a2r.immobilierdz.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RatingMapper {

    @Mapping(target = "house.id", source = "houseId")
    Rating map(RatingDTO ratingDTO);

    @Mapping(source = "house.id", target = "houseId")
    RatingDTO map(Rating rating);

    List<HouseLocationDTO> map(List<House> houseList);
}
