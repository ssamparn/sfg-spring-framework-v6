package com.sfg.course.spring6reactivewebclient.client;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.sfg.course.spring6reactivewebclient.model.BeerDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerClient {

    Flux<String> listBeer();

    Flux<Map> listBeerMap();

    Flux<JsonNode> listBeersJsonNode();

    Flux<BeerDto> listBeerDtos();

    Mono<BeerDto> getBeerById(String id);

    Flux<BeerDto> getBeerByBeerStyle(String beerStyle);

    Mono<BeerDto> createBeer(BeerDto beerDto);

    Mono<BeerDto> updateBeer(BeerDto beerDto);

    Mono<BeerDto> patchBeer(BeerDto dto);

    Mono<Void> deleteBeer(BeerDto dto);
}
