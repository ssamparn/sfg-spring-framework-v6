package com.sfg.course.spring6resttemplate.client;

import java.util.UUID;

import com.sfg.course.spring6resttemplate.model.BeerDto;
import com.sfg.course.spring6resttemplate.model.BeerStyle;
import org.springframework.data.domain.Page;

public interface BeerClient {
    Page<BeerDto> listBeers();

    Page<BeerDto> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber,
            Integer pageSize);

    BeerDto getBeerById(UUID beerId);

    BeerDto createBeer(BeerDto newDto);

    BeerDto updateBeer(BeerDto beerDto);

    void deleteBeer(UUID beerId);
}
