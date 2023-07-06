package com.sfg.course.spring6restmvc.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.sfg.course.spring6restmvc.model.csv.BeerCsvRecord;
import com.sfg.course.spring6restmvc.services.csvimpl.BeerCsvServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import static org.assertj.core.api.Assertions.assertThat;

class BeerCsvServiceImplTest {

    private BeerCsvService beerCsvService = new BeerCsvServiceImpl();

    @Test
    void convertCSV() throws FileNotFoundException {

        File file = ResourceUtils.getFile("classpath:csv/beers.csv");

        List<BeerCsvRecord> recs = beerCsvService.convertCsv(file);

        assertThat(recs.size()).isEqualTo(97);
    }
}