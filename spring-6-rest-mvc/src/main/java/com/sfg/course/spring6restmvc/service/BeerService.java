package com.sfg.course.spring6restmvc.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sfg.course.spring6restmvc.model.BeerDto;

public interface BeerService {
    List<BeerDto> listBeers();

    Optional<BeerDto> getBeerById(UUID id);

    BeerDto saveNewBeer(BeerDto beer);

    BeerDto updateBeerById(UUID beerId, BeerDto beer);

    BeerDto deleteById(UUID beerId);

    void patchBeerById(UUID beerId, BeerDto beer);
}
