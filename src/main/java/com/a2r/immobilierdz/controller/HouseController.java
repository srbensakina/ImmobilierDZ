package com.a2r.immobilierdz.controller;

import com.a2r.immobilierdz.entity.House;
import com.a2r.immobilierdz.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("houses")
@RequiredArgsConstructor
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
    public House insertHouse(@RequestBody House house) {
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

}
