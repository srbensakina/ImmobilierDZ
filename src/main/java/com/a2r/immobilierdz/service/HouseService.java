package com.a2r.immobilierdz.service;

import com.a2r.immobilierdz.dto.HouseLocationDTO;
import com.a2r.immobilierdz.entity.Address_;
import com.a2r.immobilierdz.entity.House;
import com.a2r.immobilierdz.entity.House_;
import com.a2r.immobilierdz.entity.enums.Type;
import com.a2r.immobilierdz.mapper.HouseMapper;
import com.a2r.immobilierdz.repository.AddressRepository;
import com.a2r.immobilierdz.repository.HouseRepository;
import com.a2r.immobilierdz.repository.specs.HouseSpecification;
import com.a2r.immobilierdz.repository.specs.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.a2r.immobilierdz.repository.specs.SearchOperation.*;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final AddressRepository addressRepository;
    private final HouseMapper houseMapper;

    @PreAuthorize("hasRole('owner')")
    @Transactional
    public HouseLocationDTO insertHouse(HouseLocationDTO houseLocationDto , Jwt principal) {
        House house = houseMapper.map(houseLocationDto);
        house.setOwnerId(principal.getSubject());
        addressRepository.save(house.getAddress());
        return houseMapper.map(houseRepository.save(house));
    }

    @PreAuthorize("hasRole('owner')and #houseLocationDTO.ownerId == #principal.subject")
    public void deleteHouse(HouseLocationDTO  houseLocationDTO, Jwt principal) {
        System.out.println("weeeeeee" + houseLocationDTO.getOwnerId());
        houseRepository.delete(houseMapper.map(houseLocationDTO));
    }
    @PreAuthorize("hasRole('owner') and #houseLocationDTO.ownerId == #principal.subject")
    public HouseLocationDTO updateHouse(HouseLocationDTO houseLocationDTO, Jwt principal) {
        House house = houseMapper.map(houseLocationDTO);
        addressRepository.save(house.getAddress());
        return houseMapper.map(houseRepository.save(house));
    }


    public HouseLocationDTO findHouseById(Long id) {
        return houseMapper.map(houseRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    public List<HouseLocationDTO> findAll() {
        return houseMapper.map(houseRepository.findAll());
    }


    public List<HouseLocationDTO> findHousesByCity(String city) {
        return houseMapper.map(houseRepository.findAllByAddress_City(city));
    }

    public List<House> filterHouses(String city, Integer minPrice, Integer maxPrice, Type type) {
        List<SearchCriteria> criteriaList = new ArrayList<>();

        if (city != null) criteriaList.add(new SearchCriteria(Address_.CITY, city, EQUAL));


        if (minPrice != null) criteriaList.add(new SearchCriteria(House_.PRICE, minPrice, GREATER_THAN_EQUAL));


        if (maxPrice != null) criteriaList.add(new SearchCriteria(House_.PRICE, maxPrice, LESS_THAN_EQUAL));


        if (type != null) criteriaList.add(new SearchCriteria(House_.TYPE, type, EQUAL));
        Specification<House> specification = new HouseSpecification(criteriaList);

        return houseRepository.findAll(specification);

    }


    @PreAuthorize("hasRole('owner') and #ownerId == #principal.subject")
    public List<HouseLocationDTO> findHousesByOwner(Jwt principal, String ownerId) {
        return houseMapper.map(houseRepository.findAllByOwnerId(principal.getSubject()));
    }
}