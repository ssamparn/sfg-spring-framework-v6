package com.sfg.course.spring6resttemplate.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import com.sfg.course.spring6resttemplate.client.BeerClient;
import com.sfg.course.spring6resttemplate.model.BeerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BeerTestController {

    private final BeerClient beerClient;

    @GetMapping("/api/v1/get-all-beers")
    public List<BeerDto> getAllBeers() {
        return beerClient.listBeers();
    }

}
