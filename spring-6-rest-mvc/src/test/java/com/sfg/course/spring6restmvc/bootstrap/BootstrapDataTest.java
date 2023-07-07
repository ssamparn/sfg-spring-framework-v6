package com.sfg.course.spring6restmvc.bootstrap;

import com.sfg.course.spring6restmvc.repositories.BeerRepository;
import com.sfg.course.spring6restmvc.repositories.CustomerRepository;
import com.sfg.course.spring6restmvc.services.BeerCsvService;
import com.sfg.course.spring6restmvc.services.csvimpl.BeerCsvServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BeerCsvServiceImpl.class)
class BootstrapDataTest {

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BeerCsvService csvService;

    private BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        bootstrapData = new BootstrapData(beerRepository, customerRepository, csvService);
    }

    @Test
    void Testrun() throws Exception{
        bootstrapData.run(null);

        assertThat(beerRepository.count()).isEqualTo(2413);
        assertThat(customerRepository.count()).isEqualTo(3);
    }
}





