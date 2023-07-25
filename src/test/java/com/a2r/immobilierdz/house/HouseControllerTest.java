package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.appuser.AppUser;
import com.a2r.immobilierdz.realestate.enums.Type;
import com.a2r.immobilierdz.security.config.WebSecurity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.keycloak.util.JsonSerialization.mapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = HouseController.class, excludeAutoConfiguration = {WebSecurity.class})
class HouseControllerTest {





    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HouseService houseService;

    private HouseLocationDTO houseLocationDTO;
    private HouseLocationDTO houseLocationDTO2;
    private  List<HouseLocationDTO> houseLocationDTOS;


    @BeforeEach
    void setUp(){
       houseLocationDTO =  HouseLocationDTO.builder().city("Oran").description("yada yada").doorNumber("52")
                .numberOfFloors(2).name("wa3").streetName("med med").occupied(true).type(Type.RENT).price(2424).build();


    houseLocationDTO2 =  HouseLocationDTO.builder()
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

       houseLocationDTOS = List.of(houseLocationDTO, houseLocationDTO2);
    }

    @Test
    @DisplayName("House can be created")
    @Disabled
    void testCreateHouse_whenValidDetails_returnHouse() throws Exception {

        when(houseService.insertHouse(any(HouseLocationDTO.class)  , any(String.class))).thenReturn(houseLocationDTO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/houses").with(jwt().authorities(new SimpleGrantedAuthority("owner")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(houseLocationDTO));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsAstring = mvcResult.getResponse().getContentAsString();
        HouseLocationDTO houseLocationDTO1 = new ObjectMapper().readValue(responseBodyAsAstring, HouseLocationDTO.class);

        Assertions.assertEquals(houseLocationDTO, houseLocationDTO1, "The house wasn't saved correctly");


    }

    //Might want to recheck this test !!
    @Test
    @DisplayName("All houses can be retrieved")
    void testGetAllHouses_whenValidRequest_returnAllHouses() throws Exception {





        when(houseService.findAll()).thenReturn(houseLocationDTOS);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/houses").with(jwt())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(houseLocationDTOS));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
       String responseBodyAsAstring = mvcResult.getResponse().getContentAsString();


        List<HouseLocationDTO> houseLocationDTOS1 = mapper.readValue(responseBodyAsAstring, new TypeReference<>() {
        });

        Assertions.assertEquals(houseLocationDTOS, houseLocationDTOS1,"Couldn't retrieve all the houses");

    }


    @Test
    @DisplayName("House find with Id")
    void testFindHouseById_whenValidId_returnHouse() throws Exception {

        Long id  = 1L;

        when(houseService.findHouseById(any(Long.class))).thenReturn(houseLocationDTO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/houses/{id}", id).with(jwt())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(houseLocationDTO));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsAstring = mvcResult.getResponse().getContentAsString();
        HouseLocationDTO houseLocationDTO1 = new ObjectMapper().readValue(responseBodyAsAstring, HouseLocationDTO.class);

        Assertions.assertEquals(houseLocationDTO, houseLocationDTO1, "The house couldn't be retrieved successfully");


    }


    @Test
    @DisplayName("House can be created")
    void testUpdateHouse_whenValidDetails_returnHouse() throws Exception {

        when(houseService.updateHouse(any(HouseLocationDTO.class) , any(String.class) , any(Long.class))).thenReturn(houseLocationDTO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/houses").with(jwt().authorities(new SimpleGrantedAuthority("owner")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(houseLocationDTO));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsAstring = mvcResult.getResponse().getContentAsString();
        HouseLocationDTO houseLocationDTO1 = new ObjectMapper().readValue(responseBodyAsAstring, HouseLocationDTO.class);

        Assertions.assertEquals(houseLocationDTO, houseLocationDTO1, "The house wasn't updated correctly");


    }


   @Test
    @DisplayName("House can be deleted")
    void testDeleteHouse_whenHouseExists_returnHouse() throws Exception {

        doNothing().when(houseService).deleteHouse(any(Long.class) , any(String.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/houses")
                .with(jwt().authorities(new SimpleGrantedAuthority("owner")))
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(houseLocationDTO));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(houseService, times(1)).deleteHouse(any(Long.class) , any(String.class));

    }



    @Test
    @DisplayName("House can be found by city")
    void testFindHouseByCity_whenValidDetails_returnListOfHouses() throws Exception {
           String city = "Oran";
        when(houseService.findHousesByCity(any(String.class))).thenReturn(houseLocationDTOS);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/houses/city/{city}",city).with(jwt())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(houseLocationDTOS));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsAstring = mvcResult.getResponse().getContentAsString();

        System.out.println(responseBodyAsAstring);

        List<HouseLocationDTO> houseLocationDTOS1 = mapper.readValue(responseBodyAsAstring, new TypeReference<>() {
        });
        Assertions.assertEquals(houseLocationDTOS, houseLocationDTOS1, "The list of houses by city couldn't be retrieved");

    }

}