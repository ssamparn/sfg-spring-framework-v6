package com.sfg.course.spring6reactivewebflux.repositories;

import com.sfg.course.spring6reactivewebflux.domains.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {

}
