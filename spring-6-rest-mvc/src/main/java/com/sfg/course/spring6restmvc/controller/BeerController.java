package com.sfg.course.spring6restmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.sfg.course.spring6restmvc.model.BeerDto;
import com.sfg.course.spring6restmvc.service.BeerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";

    private final BeerService beerService;

    @GetMapping(value = BEER_PATH)
    public List<BeerDto> listBeers() {
        return beerService.listBeers();
    }

}
