package com.sfg.course.spring6resttemplate.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@JsonIgnoreProperties(ignoreUnknown = true, value = "pageable")
public class BeerDtoPageImpl<BeerDto> extends PageImpl<com.sfg.course.spring6resttemplate.model.BeerDto> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BeerDtoPageImpl(@JsonProperty("content") List<com.sfg.course.spring6resttemplate.model.BeerDto> content,
                           @JsonProperty("number") int page,
                           @JsonProperty("size") int size,
                           @JsonProperty("totalElements") long total) {
        super(content, PageRequest.of(page, size), total);
    }

    public BeerDtoPageImpl(List<com.sfg.course.spring6resttemplate.model.BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerDtoPageImpl(List<com.sfg.course.spring6resttemplate.model.BeerDto> content) {
        super(content);
    }
}
