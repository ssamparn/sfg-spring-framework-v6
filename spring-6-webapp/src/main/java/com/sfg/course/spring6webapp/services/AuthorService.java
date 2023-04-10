package com.sfg.course.spring6webapp.services;

import java.util.List;

import com.sfg.course.spring6webapp.domain.Author;

public interface AuthorService {

    List<Author> findAll();
}
