package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.picture.Picture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HouseMapper {

    @Mapping(target = "address.doorNumber", source = "doorNumber")
    @Mapping(target = "address.streetName", source = "streetName")
    @Mapping(target = "address.city", source = "city")
    House map(HouseLocationDTO houseLocationDto);

    @Mapping(source = "address.doorNumber", target = "doorNumber")
    @Mapping(source = "address.streetName", target = "streetName")
    @Mapping(source = "address.city", target = "city")
    @Mapping(source = "pictures", qualifiedByName = "mapFirstPictureToFilePath", target ="mainPicture" )
    HouseLocationDTO map(House house);

    List<HouseLocationDTO> map(List<House> houseList);

    @Named("mapFirstPictureToFilePath")
    default String mapFirstPictureToFilePath(List<Picture> pictures) {
        if (pictures != null && !pictures.isEmpty()) {
            // Get the first picture from the list
            Picture firstPicture = pictures.get(0);
            return firstPicture.getFilePath();
        } else {
            // Return an empty string if there are no pictures
            return "";
        }
    }
}
