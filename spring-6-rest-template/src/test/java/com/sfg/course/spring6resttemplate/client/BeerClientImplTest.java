package com.sfg.course.spring6resttemplate.client;

import java.math.BigDecimal;

import com.sfg.course.spring6resttemplate.model.BeerDto;
import com.sfg.course.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class BeerClientImplTest {

    @Autowired
    private BeerClientImpl beerClient;

    @Test
    void listBeers() {
        Page<BeerDto> beerDtos = beerClient.listBeers();
        Assertions.assertEquals(25, beerDtos.getContent().size());
    }

    @Test
    void listBeersNoBeerName() {
        beerClient.listBeers(null, null, null, null, null);
    }

    @Test
    void listBeersWithName() {
        beerClient.listBeers("ALE", null, null, null, null);
    }

    @Test
    void testDeleteBeer() {
        BeerDto newDto = BeerDto.builder()
                .price(new BigDecimal("10.99"))
                .beerName("Mango Bobs 2")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123245")
                .build();

        BeerDto beerDto = beerClient.createBeer(newDto);

        beerClient.deleteBeer(beerDto.getId());

        assertThrows(HttpClientErrorException.class, () -> {
            //should error
            beerClient.getBeerById(beerDto.getId());
        });
    }

    @Test
    void testUpdateBeer() {

        BeerDto newDto = BeerDto.builder()
                .price(new BigDecimal("10.99"))
                .beerName("Mango Bobs 2")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123245")
                .build();

        BeerDto beerDto = beerClient.createBeer(newDto);

        final String newName = "Mango Bobs 3";
        beerDto.setBeerName(newName);
        BeerDto updatedBeer = beerClient.updateBeer(beerDto);

        assertEquals(newName, updatedBeer.getBeerName());
    }

    @Test
    void testCreateBeer() {

        BeerDto newDto = BeerDto.builder()
                .price(new BigDecimal("10.99"))
                .beerName("Mango Bobs")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123245")
                .build();

        BeerDto savedDto = beerClient.createBeer(newDto);
        assertNotNull(savedDto);
    }

    @Test
    void getBeerById() {
        Page<BeerDto> beerDTOS = beerClient.listBeers();

        BeerDto dto = beerDTOS.getContent().get(0);

        BeerDto byId = beerClient.getBeerById(dto.getId());

        assertNotNull(byId);
    }
}
