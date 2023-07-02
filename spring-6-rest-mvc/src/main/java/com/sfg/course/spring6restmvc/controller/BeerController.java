package com.sfg.course.spring6restmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

import com.sfg.course.spring6restmvc.model.BeerDto;
import com.sfg.course.spring6restmvc.service.BeerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;

    @PostMapping(value = BEER_PATH)
    public ResponseEntity<BeerDto> newBeer(@RequestBody BeerDto beer){

        log.debug("Creating New Beer - in controller");

        BeerDto savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", BEER_PATH + "/" + savedBeer.getId().toString());

        return new ResponseEntity(savedBeer, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = BEER_PATH)
    public List<BeerDto> listBeers() {
        log.debug("Get all Beer(s) - in controller");

        return beerService.listBeers();
    }

    @GetMapping(value = BEER_PATH_ID)
    public BeerDto getBeerById(@PathVariable("beerId") UUID beerId){

        log.debug("Get Beer by Id - in controller");

        return beerService.getBeerById(beerId);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity<BeerDto> updateById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beer) {

        BeerDto beerDto = beerService.updateBeerById(beerId, beer);

        return new ResponseEntity(beerDto, HttpStatus.OK);
    }

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beer){

        beerService.patchBeerById(beerId, beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity<BeerDto> deleteById(@PathVariable("beerId") UUID beerId){

        BeerDto removedBeer = beerService.deleteById(beerId);

        return new ResponseEntity(removedBeer, HttpStatus.NO_CONTENT);
    }
}
