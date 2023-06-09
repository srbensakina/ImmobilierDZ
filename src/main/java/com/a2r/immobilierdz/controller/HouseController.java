package com.a2r.immobilierdz.controller;

import com.a2r.immobilierdz.dto.HouseLocationDTO;
import com.a2r.immobilierdz.entity.House;
import com.a2r.immobilierdz.entity.enums.Type;
import com.a2r.immobilierdz.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/houses")
@RequiredArgsConstructor
@Log4j2
public class HouseController {

    private final HouseService houseService;


    @GetMapping("{id}")
    public HouseLocationDTO findHouseById(@PathVariable Long id) {
        return houseService.findHouseById(id);
    }

    @GetMapping
    public List<HouseLocationDTO> findAll() {
        return houseService.findAll();
    }

    @PostMapping
    public HouseLocationDTO insertHouse(@RequestBody HouseLocationDTO houseLocationDto, @AuthenticationPrincipal Jwt principal) {
        return houseService.insertHouse(houseLocationDto ,principal);
    }

    @PutMapping
    public HouseLocationDTO updateHouse(@RequestBody HouseLocationDTO houseLocationDTO,@AuthenticationPrincipal Jwt principal) {
        return houseService.updateHouse(houseLocationDTO, principal);
    }


    @DeleteMapping
    public void deleteHouse(@RequestBody HouseLocationDTO houseLocationDTO, @AuthenticationPrincipal Jwt principal) {
        houseService.deleteHouse(houseLocationDTO, principal);
    }


    @GetMapping("city/{city}")
    public List<HouseLocationDTO> findHouseByCity(@PathVariable String city) {
        return houseService.findHousesByCity(city);
    }

    @GetMapping("/filter")
    @ResponseBody
    public List<House> filterHouses(@RequestParam(required = false) String city, @RequestParam(value = "minPrice", required = false) Integer minPrice, @RequestParam(value = "maxPrice", required = false) Integer maxPrice, @RequestParam(required = false) Type type) {
        return houseService.filterHouses(city, minPrice, maxPrice, type);
    }

    @GetMapping("{ownerId}")
    public List<HouseLocationDTO> findHousesByOwner(@AuthenticationPrincipal Jwt principal, @PathVariable String ownerId){
        return houseService.findHousesByOwner(principal, ownerId);

    }

}
