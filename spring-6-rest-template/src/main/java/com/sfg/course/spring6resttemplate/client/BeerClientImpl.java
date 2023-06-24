package com.sfg.course.spring6resttemplate.client;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.sfg.course.spring6resttemplate.model.BeerDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class BeerClientImpl implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;

    public static final String GET_BEER_PATH = "/api/v1/beer";

    @Override
    public List<BeerDto> listBeers() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        ResponseEntity<BeerDto[]> response = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), BeerDto[].class);

        return Arrays.stream(Objects.requireNonNull(response.getBody())).toList();
    }

}
