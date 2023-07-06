package com.sfg.course.spring6restmvc.services;

import java.io.File;
import java.util.List;

import com.sfg.course.spring6restmvc.model.csv.BeerCsvRecord;

public interface BeerCsvService {
    List<BeerCsvRecord> convertCsv(File csvFile);
}
