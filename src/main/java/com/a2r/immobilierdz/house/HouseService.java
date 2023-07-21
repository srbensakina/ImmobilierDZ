package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.realestate.RealEstateService;
import com.a2r.immobilierdz.realestate.enums.Type;
import com.a2r.immobilierdz.house.specs.HouseSpecification;
import com.a2r.immobilierdz.house.specs.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.a2r.immobilierdz.house.specs.SearchOperation.*;

@Service
@RequiredArgsConstructor
public class HouseService implements RealEstateService<HouseLocationDTO>  {
    private final HouseRepository houseRepository;
    private final AddressRepository addressRepository;
    private final HouseMapper houseMapper;



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

    @PreAuthorize("hasRole('owner')")
    @Transactional
    @Override
    public HouseLocationDTO insertHouse(HouseLocationDTO houseLocationDTO, Jwt principal) {
        House house = houseMapper.map(houseLocationDTO);
        house.setOwnerId(principal.getSubject());
        addressRepository.save(house.getAddress());
        return houseMapper.map(houseRepository.save(house));
    }

   @PreAuthorize("hasRole('owner')and @houseRepository.findById(id).get().getOwnerId().equals(principal.getSubject())")
    public void deleteHouse(Long id, Jwt principal) {
        houseRepository.delete(houseRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }
    @PreAuthorize("hasRole('owner') and @houseRepository.findByName(#houseLocationDTO.getName()).get().getOwnerId().equals(principal.getSubject())")
    @Override
    public HouseLocationDTO updateHouse(HouseLocationDTO houseLocationDTO, Jwt principal) {
        House house = houseMapper.map(houseLocationDTO);
        addressRepository.save(house.getAddress());
        return houseMapper.map(houseRepository.save(house));
    }


 /*   @PreAuthorize("hasRole('owner') and #ownerId == #principal.subject")
    public List<HouseLocationDTO> findHousesByOwner(Jwt principal, String ownerId) {
        return houseMapper.map(houseRepository.findAllByOwnerId(principal.getSubject()));
    }*/
}