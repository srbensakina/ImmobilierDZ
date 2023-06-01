package com.a2r.immobilierdz.service;

import com.a2r.immobilierdz.entity.Address_;
import com.a2r.immobilierdz.entity.House;
import com.a2r.immobilierdz.entity.House_;
import com.a2r.immobilierdz.entity.enums.Type;
import com.a2r.immobilierdz.picture.PictureRepository;
import com.a2r.immobilierdz.picture.PictureService;
import com.a2r.immobilierdz.repository.AddressRepository;
import com.a2r.immobilierdz.repository.HouseRepository;
import com.a2r.immobilierdz.repository.specs.HouseSpecification;
import com.a2r.immobilierdz.repository.specs.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.a2r.immobilierdz.repository.specs.SearchOperation.*;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final AddressRepository addressRepository;


    @Transactional
    public House insertHouse(House house) throws IOException {
        addressRepository.save(house.getAddress());
        return houseRepository.save(house);
    }

    public void deleteHouse(Long id) {
        houseRepository.deleteById(id);
    }

    public House updateHouse(House house) {
        return houseRepository.save(house);
    }


    @PreAuthorize("hasRole('owner')")
    public House findHouseById(Long id) {
        return houseRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @PreAuthorize("hasRole('owner')")
    public List<House> findAll() {
        return houseRepository.findAll();
    }


    public List<House> findHousesByCity(String city) {
        return houseRepository.findAllByAddress_City(city);
    }

   public List<House> filterHouses(String city, Integer minPrice, Integer maxPrice, Type type) {
       List<SearchCriteria> criteriaList = new ArrayList<>();

       if (city != null) criteriaList.add(new SearchCriteria(Address_.CITY, city, EQUAL));


       if (minPrice != null) criteriaList.add(new SearchCriteria(House_.PRICE, minPrice, GREATER_THAN_EQUAL));


       if (maxPrice != null) criteriaList.add(new SearchCriteria(House_.PRICE, maxPrice, LESS_THAN_EQUAL));


       if (type!=null) criteriaList.add(new SearchCriteria(House_.TYPE, type, EQUAL));
       Specification<House> specification = new HouseSpecification(criteriaList);

       return houseRepository.findAll(specification);

    }

}