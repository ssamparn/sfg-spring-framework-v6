package com.sfg.course.spring6webapp.services;

import java.util.List;

import com.sfg.course.spring6webapp.domain.Book;

public interface BookService {

    List<Book> findAll();

}
