package com.a2r.immobilierdz.controller;

import com.a2r.immobilierdz.entity.Flat;
import com.a2r.immobilierdz.entity.House;

import com.a2r.immobilierdz.service.FlatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("flats")
@RequiredArgsConstructor
public class FlatController {


    private final FlatService flatService;

    @GetMapping
    public List<Flat> findAll() {
        return flatService.findAll();
    }
}
