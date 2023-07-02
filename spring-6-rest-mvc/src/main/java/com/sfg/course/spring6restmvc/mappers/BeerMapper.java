package com.sfg.course.spring6restmvc.mappers;

import com.sfg.course.spring6restmvc.entities.Beer;
import com.sfg.course.spring6restmvc.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDto dto);

    BeerDto beerToBeerDto(Beer beer);

}
