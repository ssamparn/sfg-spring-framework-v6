package com.sfg.course.spring6reactivewebflux.mappers;

import com.sfg.course.spring6reactivewebflux.domains.Beer;
import com.sfg.course.spring6reactivewebflux.models.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDto dto);

    BeerDto beerToBeerDto(Beer beer);
}
