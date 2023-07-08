package com.sfg.course.spring6restmvc.repositories;

import java.util.UUID;

import com.sfg.course.spring6restmvc.entities.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {

}
