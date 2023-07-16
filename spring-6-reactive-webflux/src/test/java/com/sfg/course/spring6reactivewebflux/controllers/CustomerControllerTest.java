package com.sfg.course.spring6reactivewebflux.controllers;

import com.sfg.course.spring6reactivewebflux.models.CustomerDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOAuth2Login;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
class CustomerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testPatchIdNotFound() {
        webTestClient
                .mutateWith(mockOAuth2Login())
                .patch()
                .uri(CustomerController.CUSTOMER_PATH_ID, 999)
                .body(Mono.just(getCustomerDto()), CustomerDto.class)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteNotFound() {
        webTestClient
                .mutateWith(mockOAuth2Login())
                .delete()
                .uri(CustomerController.CUSTOMER_PATH_ID, 999)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Order(4)
    void testUpdateCustomerBadRequest() {
        CustomerDto customerDto = getCustomerDto();
        customerDto.setCustomerName("");

        webTestClient
                .mutateWith(mockOAuth2Login())
                .put()
                .uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .body(Mono.just(customerDto), CustomerDto.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdateCustomerNotFound() {
        webTestClient
                .mutateWith(mockOAuth2Login())
                .put()
                .uri(CustomerController.CUSTOMER_PATH_ID, 999)
                .body(Mono.just(getCustomerDto()), CustomerDto.class)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetByIdNotFound() {
        webTestClient
                .mutateWith(mockOAuth2Login())
                .get().uri(CustomerController.CUSTOMER_PATH_ID, 999)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Order(999)
    void testDeleteCustomer() {
        webTestClient
                .mutateWith(mockOAuth2Login())
                .delete()
                .uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .exchange()
                .expectStatus()
                .isNoContent();
    }

    @Test
    @Order(3)
    void testUpdateCustomer() {
        webTestClient
                .mutateWith(mockOAuth2Login())
                .put()
                .uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .body(Mono.just(getCustomerDto()), CustomerDto.class)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testCreateCustomer() {

        webTestClient
                .mutateWith(mockOAuth2Login())
                .post().uri(CustomerController.CUSTOMER_PATH)
                .body(Mono.just(getCustomerDto()), CustomerDto.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost:8080/api/v2/customer/4");
    }

    @Test
    @Order(1)
    void testGetById() {
        webTestClient
                .mutateWith(mockOAuth2Login())
                .get().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "application/json")
                .expectBody(CustomerDto.class);
    }

    @Test
    @Order(2)
    void testListCustomers() {
        webTestClient
                .mutateWith(mockOAuth2Login())
                .get().uri(CustomerController.CUSTOMER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "application/json")
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }

    public static CustomerDto getCustomerDto() {
        return CustomerDto.builder()
                .customerName("Test Customer")
                .build();
    }
}