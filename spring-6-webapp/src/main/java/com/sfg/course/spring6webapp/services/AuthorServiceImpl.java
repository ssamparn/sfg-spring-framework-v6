package com.sfg.course.spring6webapp.services;

import lombok.RequiredArgsConstructor;

import java.util.List;

import com.sfg.course.spring6webapp.domain.Author;
import com.sfg.course.spring6webapp.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
