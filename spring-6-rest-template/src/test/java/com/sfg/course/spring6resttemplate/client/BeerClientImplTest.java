package com.sfg.course.spring6resttemplate.client;

import java.util.List;

import com.sfg.course.spring6resttemplate.model.BeerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeerClientImplTest {

    @Autowired
    private BeerClientImpl beerClient;

    @Test
    void listBeers() {
        List<BeerDto> beerDtos = beerClient.listBeers();
        Assertions.assertEquals(3, beerDtos.size());
        Assertions.assertEquals("Galaxy Cat", beerDtos.get(2).getBeerName());
    }
}
