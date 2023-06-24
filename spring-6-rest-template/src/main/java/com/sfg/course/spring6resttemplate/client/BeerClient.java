package com.sfg.course.spring6resttemplate.client;

import java.util.List;

import com.sfg.course.spring6resttemplate.model.BeerDto;

public interface BeerClient {
    List<BeerDto> listBeers();
}
