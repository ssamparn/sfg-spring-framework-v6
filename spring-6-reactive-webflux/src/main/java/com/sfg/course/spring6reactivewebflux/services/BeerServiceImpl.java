package com.sfg.course.spring6reactivewebflux.services;

import lombok.RequiredArgsConstructor;

import com.sfg.course.spring6reactivewebflux.mappers.BeerMapper;
import com.sfg.course.spring6reactivewebflux.models.BeerDto;
import com.sfg.course.spring6reactivewebflux.repositories.BeerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Flux<BeerDto> listBeers() {
        return beerRepository.findAll()
                .map(beerMapper::beerToBeerDto);
    }
}
