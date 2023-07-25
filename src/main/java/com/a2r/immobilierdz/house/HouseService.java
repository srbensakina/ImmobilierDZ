package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.exceptions.AddressAlreadyExistsException;
import com.a2r.immobilierdz.exceptions.AddressNotFoundException;
import com.a2r.immobilierdz.exceptions.RealEstateNotFoundException;
import com.a2r.immobilierdz.exceptions.UnauthorizedAccessException;
import com.a2r.immobilierdz.realestate.RealEstateService;
import com.a2r.immobilierdz.realestate.enums.Type;
import com.a2r.immobilierdz.house.specs.HouseSpecification;
import com.a2r.immobilierdz.house.specs.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.a2r.immobilierdz.house.specs.SearchOperation.*;

@Service
@RequiredArgsConstructor
public class HouseService implements RealEstateService<HouseLocationDTO> {
    private final HouseRepository houseRepository;
    private final AddressRepository addressRepository;
    private final HouseMapper houseMapper;

    public HouseLocationDTO findHouseById(Long id) {
        return houseMapper.map(houseRepository.findById(id).orElseThrow(() -> new RealEstateNotFoundException("House not found with ID:" + id)));
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

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @Transactional
    @Override
    public HouseLocationDTO insertHouse(HouseLocationDTO houseLocationDTO, String principal) {
        House house = houseMapper.map(houseLocationDTO);
        house.setOwnerId(Long.valueOf(principal));
        addressRepository.save(house.getAddress());
        return houseMapper.map(houseRepository.save(house));
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @Override
    public void deleteHouse(Long id, String principal) {
        House house = houseRepository.findById(id).orElseThrow(() -> new RealEstateNotFoundException("House not found"));
        if (house.getOwnerId().equals(Long.valueOf(principal))) {
            houseRepository.delete(house);
            addressRepository.delete(house.getAddress());
        } else {
            throw new UnauthorizedAccessException("You are not authorized to delete this house.");
        }
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @Override
    public HouseLocationDTO updateHouse(HouseLocationDTO houseLocationDTO, String principal, Long houseId) {
        House house = houseRepository.findById(houseId).orElseThrow(() -> new RealEstateNotFoundException("House not found"));
        Address address = addressRepository.findById(house.getAddress().getId()).orElseThrow(() -> new AddressNotFoundException("Address not found"));
        if (!house.getOwnerId().equals(Long.valueOf(principal))) {
            throw new UnauthorizedAccessException("You are not authorized to update this house.");
        }
        address.setCity(houseLocationDTO.getCity());
        address.setDoorNumber(houseLocationDTO.getDoorNumber());
        address.setStreetName(houseLocationDTO.getStreetName());
        house.setNumberOfFloors(houseLocationDTO.getNumberOfFloors());
        house.setDescription(houseLocationDTO.getDescription());
        house.setName(houseLocationDTO.getName());
        house.setPrice(houseLocationDTO.getPrice());
        house.setOccupied(houseLocationDTO.getOccupied());
        house.setType(houseLocationDTO.getType());
        addressRepository.save(address);
        return houseMapper.map(houseRepository.save(house));
    }


    @PreAuthorize("hasRole('ROLE_OWNER')")
    public List<HouseLocationDTO> findHousesByOwnerId(Long ownerId, String principal) {
        if (ownerId.equals(Long.valueOf(principal))) {
            return houseMapper.map(houseRepository.findAllByOwnerId(ownerId));
        } else {
            throw new UnauthorizedAccessException("You are only authorized to see the houses you own.");
        }
    }
}
