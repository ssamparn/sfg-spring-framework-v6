package com.sfg.course.spring6reactivewebflux.repositories;

import com.sfg.course.spring6reactivewebflux.domains.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}
