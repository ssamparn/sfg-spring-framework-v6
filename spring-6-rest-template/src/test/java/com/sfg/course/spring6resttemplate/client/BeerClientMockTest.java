package com.sfg.course.spring6resttemplate.client;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfg.course.spring6resttemplate.config.RestTemplateConfig;
import com.sfg.course.spring6resttemplate.model.BeerDto;
import com.sfg.course.spring6resttemplate.model.BeerDtoPageImpl;
import com.sfg.course.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.MockServerRestTemplateCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withAccepted;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withResourceNotFound;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(BeerClientImpl.class)
@Import(RestTemplateConfig.class)
public class BeerClientMockTest {

    private BeerDto beerDto;
    private String dtoJson;

    private static final String URL = "http://localhost:8080";

    private BeerClient beerClient;

    private MockRestServiceServer mockServer;

    @Autowired
    RestTemplateBuilder restTemplateBuilderConfigured;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    RestTemplateBuilder mockRestTemplateBuilder = new RestTemplateBuilder(new MockServerRestTemplateCustomizer());

    @BeforeEach
    void setUp() throws JsonProcessingException {
        RestTemplate restTemplate = restTemplateBuilderConfigured.build();
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
        when(mockRestTemplateBuilder.build()).thenReturn(restTemplate);
        beerClient = new BeerClientImpl(mockRestTemplateBuilder);
        beerDto = getBeerDto();
        dtoJson = objectMapper.writeValueAsString(beerDto);
    }

    @Test
    void testListBeersWithQueryParam() throws JsonProcessingException {
        String response = objectMapper.writeValueAsString(getPage());

        URI uri = UriComponentsBuilder.fromHttpUrl(URL + BeerClientImpl.GET_BEER_PATH)
                .queryParam("beerName", "ALE")
                .build().toUri();

        mockServer.expect(method(HttpMethod.GET))
                .andExpect(requestTo(uri))
                .andExpect(queryParam("beerName", "ALE"))
                .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));

        Page<BeerDto> responsePage = beerClient
                .listBeers("ALE", null, null, null, null);

        assertThat(responsePage.getContent().size()).isEqualTo(1);
    }

    @Test
    void testDeleteNotFound() {
        mockServer.expect(method(HttpMethod.DELETE))
                .andExpect(requestToUriTemplate(URL + BeerClientImpl.GET_BEER_BY_ID_PATH,
                        beerDto.getId()))
                .andRespond(withResourceNotFound());

        assertThrows(HttpClientErrorException.class, () -> {
            beerClient.deleteBeer(beerDto.getId());
        });

        mockServer.verify();
    }

    @Test
    void testDeleteBeer() {
        mockServer.expect(method(HttpMethod.DELETE))
                .andExpect(requestToUriTemplate(URL + BeerClientImpl.GET_BEER_BY_ID_PATH,
                        beerDto.getId()))
                .andRespond(withNoContent());

        beerClient.deleteBeer(beerDto.getId());

        mockServer.verify();
    }

    @Test
    void testUpdateBeer() {
        mockServer.expect(method(HttpMethod.PUT))
                .andExpect(requestToUriTemplate(URL + BeerClientImpl.GET_BEER_BY_ID_PATH,
                        beerDto.getId()))
                .andRespond(withNoContent());

        mockGetOperation();

        BeerDto responseDto = beerClient.updateBeer(beerDto);
        assertThat(responseDto.getId()).isEqualTo(beerDto.getId());
    }

    @Test
    void testCreateBeer()  {
        URI uri = UriComponentsBuilder.fromPath(BeerClientImpl.GET_BEER_BY_ID_PATH)
                .build(beerDto.getId());

        mockServer.expect(method(HttpMethod.POST))
                .andExpect(requestTo(URL +
                        BeerClientImpl.GET_BEER_PATH))
                .andRespond(withAccepted().location(uri));

        mockGetOperation();

        BeerDto responseDto = beerClient.createBeer(beerDto);
        assertThat(responseDto.getId()).isEqualTo(beerDto.getId());
    }

    @Test
    void testGetById() {
        mockGetOperation();

        BeerDto responseDto = beerClient.getBeerById(beerDto.getId());
        assertThat(responseDto.getId()).isEqualTo(beerDto.getId());
    }

    @Test
    void testListBeers() throws JsonProcessingException {
        String payload = objectMapper.writeValueAsString(getPage());

        mockServer.expect(method(HttpMethod.GET))
                .andExpect(requestTo(URL + BeerClientImpl.GET_BEER_PATH))
                .andRespond(withSuccess(payload, MediaType.APPLICATION_JSON));

        Page<BeerDto> dtos = beerClient.listBeers();
        assertThat(dtos.getContent().size()).isGreaterThan(0);
    }

    private BeerDto getBeerDto() {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .price(new BigDecimal("10.99"))
                .beerName("Mango Bobs")
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("123245")
                .build();
    }

    private void mockGetOperation() {
        mockServer.expect(method(HttpMethod.GET))
                .andExpect(requestToUriTemplate(URL +
                        BeerClientImpl.GET_BEER_BY_ID_PATH, beerDto.getId()))
                .andRespond(withSuccess(dtoJson, MediaType.APPLICATION_JSON));
    }

    private BeerDtoPageImpl getPage(){
        return new BeerDtoPageImpl(Arrays.asList(getBeerDto()), 1, 25, 1);
    }
}
