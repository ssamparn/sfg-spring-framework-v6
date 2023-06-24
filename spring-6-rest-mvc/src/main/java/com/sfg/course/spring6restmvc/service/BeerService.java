package com.sfg.course.spring6restmvc.service;

import java.util.List;

import com.sfg.course.spring6restmvc.model.BeerDto;

public interface BeerService {
    List<BeerDto> listBeers();
}
