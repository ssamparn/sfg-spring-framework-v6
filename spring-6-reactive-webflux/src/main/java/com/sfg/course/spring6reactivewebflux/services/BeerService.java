package com.sfg.course.spring6reactivewebflux.services;

import com.sfg.course.spring6reactivewebflux.models.BeerDto;
import reactor.core.publisher.Flux;

public interface BeerService {

    Flux<BeerDto> listBeers();
}
