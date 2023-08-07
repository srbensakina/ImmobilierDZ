package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.realestate.enums.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


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
    public void testCreateHouse_whenValidDetails_returnHouse() {
        String principal = "123";

        house.setOwnerId(Long.valueOf(principal));

        // Mock the behavior of houseMapper
        when(houseMapper.map(houseLocationDTO)).thenReturn(house);

        // Mock the behavior of houseRepository
        when(houseRepository.save(any(House.class))).thenReturn(house);

        // Call the method to be tested
        HouseLocationDTO result = houseService.insertHouse(houseLocationDTO, principal);

        // Verify the interactions and assertions
        verify(houseMapper, times(2)).map(houseLocationDTO);
        verify(addressRepository, times(1)).save(any(Address.class));
        verify(houseRepository, times(1)).save(house);
        assertThat(result).isEqualTo(houseLocationDTO);

    }


}





