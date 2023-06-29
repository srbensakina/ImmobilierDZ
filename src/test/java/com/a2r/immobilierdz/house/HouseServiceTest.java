package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.entity.RealEstate;
import com.a2r.immobilierdz.entity.enums.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.security.RunAs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HouseServiceTest {


    @Spy
    private HouseMapper houseMapper;

    @Mock
    private HouseRepository houseRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private HouseService houseService;

    private HouseLocationDTO houseLocationDTO;

    private House house;

    @BeforeEach
    void setUp() {
        houseMapper = Mappers.getMapper(HouseMapper.class);

        houseLocationDTO = HouseLocationDTO.builder()
                .city("Algiers")
                .description("another description")
                .doorNumber("10")
                .numberOfFloors(1)
                .name("house2")
                .streetName("abc street")
                .occupied(false)
                .type(Type.SALE)
                .price(500000)
                .build();
        house = House.builder().id(1L).description("another description")
                .numberOfFloors(2).name("house2").occupied(true).ownerId("a").type(Type.SALE).price(500000).numberOfFloors(1).build();
    }


    @Test
    public void testCreateHouse_whenValidDetails_returnHouse() {

        when(houseRepository.save(house)).thenReturn(house);

        HouseLocationDTO result = houseService.insertHouse(houseLocationDTO, Mockito.mock(Jwt.class));

        verify(addressRepository).save(house.getAddress());

        assertNotNull(result);
        verify(houseMapper).map(houseLocationDTO);

    }


}