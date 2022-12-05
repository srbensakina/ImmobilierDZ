package com.a2r.immobilierdz.service;

import com.a2r.immobilierdz.entity.Address;
import com.a2r.immobilierdz.entity.House;
import com.a2r.immobilierdz.repository.AddressRepository;
import com.a2r.immobilierdz.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final AddressRepository addressRepository;

    public House insertHouse(House house) {
        addressRepository.save(house.getAddress());
        return houseRepository.save(house);
    }

    public void deleteHouse(Long id) {
        houseRepository.deleteById(id);
    }

    public House updateHouse(House house) {
        return houseRepository.save(house);
    }

    public House findHouseById(Long id) {
        return houseRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<House> findAll() {
        return houseRepository.findAll();
    }


    public List<House> findHousesByCity(String city) {
        return houseRepository.findAllByAddress_City(city);//.orElseThrow(NoSuchElementException::new);
    }

}