package com.a2r.immobilierdz.house;

import com.a2r.immobilierdz.appuser.AppUser;
import com.a2r.immobilierdz.appuser.AppUserRole;
import com.a2r.immobilierdz.appuser.AppUserService;
import com.a2r.immobilierdz.realestate.enums.Type;
import com.a2r.immobilierdz.security.jwt.JwtAuthorizationFilter;
import com.a2r.immobilierdz.security.jwt.JwtUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.keycloak.util.JsonSerialization.mapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(controllers = HouseController.class, excludeAutoConfiguration = {WebSecurity.class})
@WebMvcTest(value = HouseController.class, includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtUtil.class)})
class HouseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HouseService houseService;

    @MockBean
    private AppUserService appUserService;

    @Autowired
    private JwtAuthorizationFilter filter;
    private HouseLocationDTO houseLocationDTO;
    private HouseLocationDTO houseLocationDTO2;

    private House house;
    private List<HouseLocationDTO> houseLocationDTOS;

    private AppUser user;


    @BeforeEach
    void setUp() {

        houseLocationDTO = HouseLocationDTO.builder().city("Oran").description("yada yada").doorNumber("52").numberOfFloors(2).name("wa3").streetName("med med").occupied(true).type(Type.RENT).price(2424).build();
        Address address = Address.builder().id(1L).city("Oran").streetName("med med").doorNumber("52").build();

        house = House.builder().id(1L).description("yada yada").numberOfFloors(2).name("wa3").occupied(true).type(Type.RENT).price(2424).address(address).build();

        houseLocationDTO2 = HouseLocationDTO.builder().city("Algiers").description("another description").doorNumber("10").numberOfFloors(1).name("house2").streetName("abc street").occupied(false).type(Type.SALE).price(500000).build();

        houseLocationDTOS = List.of(houseLocationDTO, houseLocationDTO2);
        AppUser.builder().id(1L).email("email@email.com").appUserRole(AppUserRole.OWNER).password("password").lastName("Last name").firstName("First name").enabled(true).build();

        user = AppUser.builder().id(1L).appUserRole(AppUserRole.OWNER).enabled(true).id(1L).firstName("First Name").lastName("LastName").password("password").email("email@email.com").locked(false).build();

    }



    @Test
    @DisplayName("House can be created")
    void testCreateHouse_whenValidDetails_returnHouse() throws Exception {
        // when(appUserService.loadUserByUsername(any(String.class))).thenReturn(user);
        when(houseService.insertHouse(houseLocationDTO, user.getId().toString())).thenReturn(houseLocationDTO);
        when(appUserService.loadUserByUsername(user.getEmail())).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/houses")
                .content(new ObjectMapper().writeValueAsString(houseLocationDTO)).with(jwt().authorities(new SimpleGrantedAuthority("ROLE_OWNER")))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andReturn();

        // TOOD : fix the body being empty
       // String responseBodyAstring = mvcResult.getResponse().getContentAsString();
        // HouseLocationDTO houseLocationDTO1 = new ObjectMapper().readValue(responseBodyAstring, HouseLocationDTO.class);

         // Assertions.assertEquals(houseLocationDTO, houseLocationDTO1, "The house wasn't saved correctly");
    }

    @Test
    @DisplayName("All houses can be retrieved")
    void testGetAllHouses_whenValidRequest_returnAllHouses(Pageable pageable) throws Exception {
       // when(houseService.findAll()).thenReturn(houseLocationDTOS);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1//houses")
                .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_OWNER"))
                ).contentType(APPLICATION_JSON).accept(APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful()).andReturn();
        String responseBodyAsAstring = mvcResult.getResponse().getContentAsString();


        List<HouseLocationDTO> houseLocationDTOS1 = mapper.readValue(responseBodyAsAstring, new TypeReference<>() {
        });
        Assertions.assertEquals(houseLocationDTOS, houseLocationDTOS1, "Couldn't retrieve all the houses");

    }


    @Test

    @DisplayName("House find with Id")
    void testFindHouseById_whenValidId_returnHouse() throws Exception {

        Long id = 1L;

        when(houseService.findHouseById(any(Long.class))).thenReturn(houseLocationDTO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1//houses/{id}", id).with(jwt()).contentType(APPLICATION_JSON).accept(APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(houseLocationDTO));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsAstring = mvcResult.getResponse().getContentAsString();
        HouseLocationDTO houseLocationDTO1 = new ObjectMapper().readValue(responseBodyAsAstring, HouseLocationDTO.class);

        Assertions.assertEquals(houseLocationDTO, houseLocationDTO1, "The house couldn't be retrieved successfully");


    }


    @Test
    @DisplayName("House can be created")
    void testUpdateHouse_whenValidIdAndOwner_returnUpdatedHouse() throws Exception {
        Long id = 1L;
        when(houseService.updateHouse(any(HouseLocationDTO.class), any(String.class), any(Long.class))).thenReturn(houseLocationDTO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/houses/{houseId}" , id)
                .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_OWNER")))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(houseLocationDTO));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful()).andReturn();
       // String responseBodyAsAstring = mvcResult.getResponse().getContentAsString();
      //   HouseLocationDTO houseLocationDTO1 = new ObjectMapper().readValue(responseBodyAsAstring, HouseLocationDTO.class);

      //   Assertions.assertEquals(houseLocationDTO, houseLocationDTO1, "The house wasn't updated correctly");


    }


    @Test
    @DisplayName("House can be deleted")
    void testDeleteHouse_whenHouseExists_returnHouse() throws Exception {
        Long id = 1L;
        doNothing().when(houseService).deleteHouse(anyLong(), anyString());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/houses/{id}" , id)
               .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_OWNER")))
                        .contentType(APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(houseLocationDTO));

        mockMvc.perform(requestBuilder).andExpect(status().isNoContent()).andReturn();

      //  verify(houseService, times(1)).deleteHouse(anyLong(), any(String.class));

    }


    @Test
    @DisplayName("House can be found by city")
    void testFindHouseByCity_whenValidDetails_returnListOfHouses() throws Exception {
        String city = "Oran";
        when(houseService.findHousesByCity(any(String.class))).thenReturn(houseLocationDTOS);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/houses/city/{city}", city).with(jwt()).contentType(APPLICATION_JSON).accept(APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(houseLocationDTOS));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsAstring = mvcResult.getResponse().getContentAsString();

        System.out.println(responseBodyAsAstring);

        List<HouseLocationDTO> houseLocationDTOS1 = mapper.readValue(responseBodyAsAstring, new TypeReference<>() {
        });
        Assertions.assertEquals(houseLocationDTOS, houseLocationDTOS1, "The list of houses by city couldn't be retrieved");

    }

}