package com.a2r.immobilierdz.house;
import com.a2r.immobilierdz.house.HouseLocationDTO;
import com.a2r.immobilierdz.house.House;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HouseMapper {

    @Mapping(target = "address.doorNumber", source = "doorNumber")
    @Mapping(target = "address.streetName", source = "streetName")
    @Mapping(target = "address.city", source = "city")
   // @Mapping(target = "ownerId", source = "ownerId")
    House map(HouseLocationDTO houseLocationDto);


    @Mapping(source = "address.doorNumber", target = "doorNumber")
    @Mapping(source = "address.streetName", target = "streetName")
    @Mapping(source = "address.city", target = "city")
    //@Mapping(target = "ownerId", source = "ownerId")
    HouseLocationDTO map(House house);

    List<HouseLocationDTO> map(List<House> houseList);
}
