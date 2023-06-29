package com.a2r.immobilierdz.flat;

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
