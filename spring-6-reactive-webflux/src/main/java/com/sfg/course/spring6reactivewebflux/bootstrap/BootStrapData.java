package com.sfg.course.spring6reactivewebflux.bootstrap;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sfg.course.spring6reactivewebflux.domains.Beer;
import com.sfg.course.spring6reactivewebflux.domains.Customer;
import com.sfg.course.spring6reactivewebflux.repositories.BeerRepository;
import com.sfg.course.spring6reactivewebflux.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BootStrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCustomerData();

        beerRepository.count().subscribe(count -> {
            System.out.println("Beer Count is: " + count);
        });

        customerRepository.count().subscribe(count -> {
            System.out.println("Customer Count is: " + count);
        });
    }

    private void loadBeerData() {
        beerRepository.count().subscribe(count -> {
            if (count == 0) {
                Beer beer1 = Beer.builder()
                        .beerName("Galaxy Cat")
                        .beerStyle("Pale Ale")
                        .upc("12356")
                        .price(new BigDecimal("12.99"))
                        .quantityOnHand(122)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer2 = Beer.builder()
                        .beerName("Crank")
                        .beerStyle("Pale Ale")
                        .upc("12356222")
                        .price(new BigDecimal("11.99"))
                        .quantityOnHand(392)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer3 = Beer.builder()
                        .beerName("Sunshine City")
                        .beerStyle("IPA")
                        .upc("12356")
                        .price(new BigDecimal("13.99"))
                        .quantityOnHand(144)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                beerRepository.save(beer1).subscribe();
                beerRepository.save(beer2).subscribe();
                beerRepository.save(beer3).subscribe();
            }
        });
    }

    private void loadCustomerData() {
        customerRepository.count().subscribe(count -> {
            if(count == 0) {
                customerRepository.save(Customer.builder()
                                .customerName("Customer 1")
                                .build())
                        .subscribe();

                customerRepository.save(Customer.builder()
                                .customerName("Customer 2")
                                .build())
                        .subscribe();

                customerRepository.save(Customer.builder()
                                .customerName("Customer 3")
                                .build())
                        .subscribe();
            }
        });
    }
}
