package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.realestate.enums.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.jwt.Jwt;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HouseServiceTest {
    @Spy
    private HouseMapper houseMapper = new HouseMapperImpl();

    @Mock
    private HouseRepository houseRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private HouseService houseService;

    private HouseLocationDTO houseLocationDTO;

    private House house;
    private Address address;


    @BeforeEach
    void setUp() {
        address = Address.builder().streetName("abc street").city("Algiers").doorNumber("10").build();

       houseLocationDTO = HouseLocationDTO.builder().city("Algiers").description("another description").doorNumber("10").numberOfFloors(1).name("house2").streetName("abc street").occupied(false).type(Type.SALE).price(500000).build();
        house = House.builder().description("another description").numberOfFloors(2).name("house2").occupied(false).type(Type.SALE).price(500000).numberOfFloors(1).address(address).build();
    }

    @Test
    @Disabled
    public void testCreateHouse_whenValidDetails_returnHouse() {

        // Stub the behavior of houseRepository.save() to return the expected house object
        when(houseRepository.save(Mockito.any(House.class))).thenReturn(house);

        // Create a mock Jwt object
        Jwt jwtMock = Mockito.mock(Jwt.class);

        // Call the insertHouse method
     //   HouseLocationDTO result = houseService.insertHouse(houseLocationDTO, jwtMock);

        // Verify the interactions and assertions
        verify(addressRepository).save(house.getAddress());
    //    assertNotNull(result);
        verify(houseMapper).map(houseLocationDTO);
     //   assertThat(result).isEqualTo(houseLocationDTO);
    }


}





