package com.sfg.course.spring6webapp.services;

import java.util.List;

import com.sfg.course.spring6webapp.entity.BookEntity;

public interface BookService {

    List<BookEntity> findAll();

}
