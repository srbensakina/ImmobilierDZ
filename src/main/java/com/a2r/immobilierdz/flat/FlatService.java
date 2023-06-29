package com.a2r.immobilierdz.flat;


import com.a2r.immobilierdz.flat.Flat;
import com.a2r.immobilierdz.house.AddressRepository;
import com.a2r.immobilierdz.flat.FlatRepository;
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
