package com.sfg.course.spring6restmvc.services.csvimpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.sfg.course.spring6restmvc.model.csv.BeerCsvRecord;
import com.sfg.course.spring6restmvc.services.BeerCsvService;
import org.springframework.stereotype.Service;

@Service
public class BeerCsvServiceImpl implements BeerCsvService {
    @Override
    public List<BeerCsvRecord> convertCsv(File csvFile) {
        try {
            List<BeerCsvRecord> beerCSVRecords = new CsvToBeanBuilder<BeerCsvRecord>(new FileReader(csvFile))
                    .withType(BeerCsvRecord.class)
                    .build().parse();
            return beerCSVRecords;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
