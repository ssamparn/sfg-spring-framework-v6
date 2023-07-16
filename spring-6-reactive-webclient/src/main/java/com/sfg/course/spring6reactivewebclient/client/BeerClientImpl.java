package com.sfg.course.spring6reactivewebclient.client;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.sfg.course.spring6reactivewebclient.model.BeerDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BeerClientImpl implements BeerClient {

    public static final String BEER_PATH = "/api/v2/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final WebClient webClient;

    public BeerClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Mono<BeerDto> patchBeer(BeerDto dto) {
        return webClient.patch()
                .uri(uriBuilder -> uriBuilder.path(BEER_PATH_ID).build(dto.getId()))
                .body(Mono.just(dto), BeerDto.class)
                .retrieve()
                .toBodilessEntity()
                .flatMap(voidResponseEntity -> getBeerById(dto.getId()));
    }

    @Override
    public Mono<Void> deleteBeer(BeerDto dto) {
        return webClient.delete()
                .uri(uriBuilder -> uriBuilder.path(BEER_PATH_ID).build(dto.getId()))
                .retrieve()
                .toBodilessEntity()
                .then();
    }

    @Override
    public Mono<BeerDto> updateBeer(BeerDto BeerDto) {
        return webClient.put()
                .uri(uriBuilder -> uriBuilder.path(BEER_PATH_ID).build(BeerDto.getId()))
                .body(Mono.just(BeerDto), BeerDto.class)
                .retrieve()
                .toBodilessEntity()
                .flatMap(voidResponseEntity -> getBeerById(BeerDto.getId()));
    }

    @Override
    public Mono<BeerDto> createBeer(BeerDto BeerDto) {
        return webClient.post()
                .uri(BEER_PATH)
                .body(Mono.just(BeerDto), BeerDto.class)
                .retrieve()
                .toBodilessEntity()
                .flatMap(voidResponseEntity -> Mono.just(voidResponseEntity
                        .getHeaders().get("Location").get(0)))
                .map(path -> path.split("/")[path.split("/").length - 1])
                .flatMap(this::getBeerById);
    }

    @Override
    public Flux<BeerDto> getBeerByBeerStyle(String beerStyle) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path(BEER_PATH)
                        .queryParam("beerStyle", beerStyle).build())
                .retrieve()
                .bodyToFlux(BeerDto.class);
    }

    @Override
    public Mono<BeerDto> getBeerById(String id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(BEER_PATH_ID)
                        .build(id))
                .retrieve()
                .bodyToMono(BeerDto.class);
    }

    @Override
    public Flux<BeerDto> listBeerDtos() {
        return webClient.get().uri(BEER_PATH)
                .retrieve().bodyToFlux(BeerDto.class);
    }

    @Override
    public Flux<JsonNode> listBeersJsonNode() {
        return webClient.get().uri(BEER_PATH)
                .retrieve().bodyToFlux(JsonNode.class);
    }

    @Override
    public Flux<Map> listBeerMap() {
        return webClient.get().uri(BEER_PATH)
                .retrieve().bodyToFlux(Map.class);
    }

    @Override
    public Flux<String> listBeer() {
        return webClient.get().uri(BEER_PATH)
                .retrieve().bodyToFlux(String.class);
    }
}
