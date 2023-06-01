package com.a2r.immobilierdz.controller;

import com.a2r.immobilierdz.entity.House;
import com.a2r.immobilierdz.entity.enums.Type;
import com.a2r.immobilierdz.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/houses")
@RequiredArgsConstructor
@Log4j2
public class HouseController {

    private final HouseService houseService;


    @GetMapping("{id}")
    public House findHouseById(@PathVariable Long id) {
        return houseService.findHouseById(id);
    }
    @GetMapping
    public List<House> findAll() {
        return houseService.findAll();
    }

    @PostMapping
    public House insertHouse(@RequestBody House house) throws IOException {
        return houseService.insertHouse(house);
    }


    @PutMapping
    public House updateHouse(@RequestBody House house) {
        return houseService.updateHouse(house);
    }


    @DeleteMapping("{id}")
    public void deleteHouse(@PathVariable Long id) {
        houseService.deleteHouse(id);
    }


    @GetMapping("city/{city}")
    public List<House> findHouseByCity(@PathVariable String city) {
        return houseService.findHousesByCity(city);
    }

    @GetMapping("/filter")
    @ResponseBody
    public List<House> filterHouses(@RequestParam(required = false) String city, @RequestParam(value = "minPrice", required = false) Integer minPrice, @RequestParam(value = "maxPrice", required = false) Integer maxPrice, @RequestParam(required = false) Type type) {
        return houseService.filterHouses(city, minPrice, maxPrice, type);
    }

}
