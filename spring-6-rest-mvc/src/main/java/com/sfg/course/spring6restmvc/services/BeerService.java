package com.sfg.course.spring6restmvc.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sfg.course.spring6restmvc.model.BeerDto;
import com.sfg.course.spring6restmvc.model.BeerStyle;

public interface BeerService {
    List<BeerDto> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory);

    Optional<BeerDto> getBeerById(UUID id);

    BeerDto saveNewBeer(BeerDto beer);

    Optional<BeerDto> updateBeerById(UUID beerId, BeerDto beer);

    Boolean deleteById(UUID beerId);

    Optional<BeerDto> patchBeerById(UUID beerId, BeerDto beer);
}
