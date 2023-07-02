package com.sfg.course.spring6restmvc.repositories;

import java.util.UUID;

import com.sfg.course.spring6restmvc.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
