package com.sfg.course.spring6webapp.services;

import java.util.List;

import com.sfg.course.spring6webapp.entity.AuthorEntity;

public interface AuthorService {

    List<AuthorEntity> findAll();
}
