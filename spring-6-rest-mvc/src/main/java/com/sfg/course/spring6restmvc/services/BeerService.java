package com.sfg.course.spring6restmvc.services;

import java.util.Optional;
import java.util.UUID;

import com.sfg.course.spring6restmvc.model.BeerDto;
import com.sfg.course.spring6restmvc.model.BeerStyle;
import org.springframework.data.domain.Page;

public interface BeerService {
    Page<BeerDto> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize);

    Optional<BeerDto> getBeerById(UUID id);

    BeerDto saveNewBeer(BeerDto beer);

    Optional<BeerDto> updateBeerById(UUID beerId, BeerDto beer);

    Boolean deleteById(UUID beerId);

    Optional<BeerDto> patchBeerById(UUID beerId, BeerDto beer);
}
