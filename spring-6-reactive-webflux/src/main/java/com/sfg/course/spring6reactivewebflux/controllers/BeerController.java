package com.sfg.course.spring6reactivewebflux.controllers;

import lombok.RequiredArgsConstructor;

import com.sfg.course.spring6reactivewebflux.models.BeerDto;
import com.sfg.course.spring6reactivewebflux.services.BeerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class BeerController {

    public static final String BEER_PATH = "/api/v2/beer";

    private final BeerService beerService;

    @GetMapping(BEER_PATH)
    public Flux<BeerDto> listBeers(){
        return beerService.listBeers();
    }

}
