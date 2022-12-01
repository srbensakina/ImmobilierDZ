package com.a2r.immobilierdz.service;

import com.a2r.immobilierdz.entity.Address;
import com.a2r.immobilierdz.entity.House;
import com.a2r.immobilierdz.repository.AddressRepository;
import com.a2r.immobilierdz.repository.HouseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@SpringBootTest
class HouseServiceTest {

    @InjectMocks
    HouseService houseService;

    @Mock
     HouseRepository houseRepository;
    @Mock
    AddressRepository addressRepository;
    @Test
    void insertHouse() {
        when(houseRepository.save(any(House.class))).thenReturn(null);
        when(addressRepository.save(any(Address.class))).thenReturn(null);

        houseService.insertHouse(new House());

    }
}