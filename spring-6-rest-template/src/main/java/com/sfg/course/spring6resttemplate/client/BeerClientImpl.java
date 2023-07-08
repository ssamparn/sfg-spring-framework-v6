package com.sfg.course.spring6resttemplate.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

import com.sfg.course.spring6resttemplate.model.BeerDto;
import com.sfg.course.spring6resttemplate.model.BeerDtoPageImpl;
import com.sfg.course.spring6resttemplate.model.BeerStyle;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;

    public static final String GET_BEER_PATH = "/api/v1/beer";
    public static final String GET_BEER_BY_ID_PATH = "/api/v1/beer/{beerId}";

    @Override
    public Page<BeerDto> listBeers() {
        return this.listBeers(null, null, null, null, null);
    }

    @Override
    public Page<BeerDto> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerName != null) {
            uriComponentsBuilder.queryParam("beerName", beerName);
        }

        if (beerStyle != null) {
            uriComponentsBuilder.queryParam("beerStyle", beerStyle);
        }

        if (showInventory != null) {
            uriComponentsBuilder.queryParam("showInventory", beerStyle);
        }

        if (pageNumber != null) {
            uriComponentsBuilder.queryParam("pageNumber", beerStyle);
        }

        if (pageSize != null) {
            uriComponentsBuilder.queryParam("pageSize", beerStyle);
        }

        ResponseEntity<BeerDtoPageImpl> response = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), BeerDtoPageImpl.class);

        log.info(Objects.requireNonNull(response.getBody().toList().toString()));

        return response.getBody();
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.getForObject(GET_BEER_BY_ID_PATH, BeerDto.class, beerId);
    }

    @Override
    public BeerDto createBeer(BeerDto newDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        URI uri = restTemplate.postForLocation(GET_BEER_PATH, newDto);

        return restTemplate.getForObject(uri.getPath(), BeerDto.class);
    }

    @Override
    public BeerDto updateBeer(BeerDto beerDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.put(GET_BEER_BY_ID_PATH, beerDto, beerDto.getId());
        return getBeerById(beerDto.getId());
    }

    @Override
    public void deleteBeer(UUID beerId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(GET_BEER_BY_ID_PATH, beerId);
    }

}
