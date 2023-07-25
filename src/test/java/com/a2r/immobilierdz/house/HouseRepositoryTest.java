package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.realestate.enums.Type;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class HouseRepositoryTest {

    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private AddressRepository addressRepository;

    @AfterEach
    void tearDown() {
        houseRepository.deleteAll();
        addressRepository.deleteAll();
    }

    @Test
    void itShouldFindAllByAddress_City(){

        Address address = Address.builder().id(2L).city("oran").doorNumber("52").streetName("amir Aek").build();

        House house = House.builder().id(2L).description("another description")
               .numberOfFloors(2).name("house2").occupied(true).ownerId(1L).type(Type.SALE).price(500000).numberOfFloors(1).address(address).build();

       addressRepository.save(house.getAddress());
       houseRepository.save(house);


        List<House> allByAddress_city = houseRepository.findAllByAddress_City("oran");


        assertThat(allByAddress_city.size()).isEqualTo(1);


    }
}
