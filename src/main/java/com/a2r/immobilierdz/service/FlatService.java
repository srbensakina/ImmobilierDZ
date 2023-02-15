package com.a2r.immobilierdz.service;


import com.a2r.immobilierdz.entity.Flat;
import com.a2r.immobilierdz.entity.House;
import com.a2r.immobilierdz.repository.AddressRepository;
import com.a2r.immobilierdz.repository.FlatRepository;
import com.a2r.immobilierdz.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class FlatService {
    private final FlatRepository flatRepository;
    private final AddressRepository addressRepository;
    public List<Flat> findAll() {
        return flatRepository.findAll();
    }

}
