package com.sfg.course.spring6restmvc.mappers;

import com.sfg.course.spring6restmvc.entities.Customer;
import com.sfg.course.spring6restmvc.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDto dto);

    CustomerDto customerToCustomerDto(Customer customer);
}
