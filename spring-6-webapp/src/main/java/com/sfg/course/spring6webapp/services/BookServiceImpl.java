package com.sfg.course.spring6webapp.services;

import lombok.RequiredArgsConstructor;

import java.util.List;

import com.sfg.course.spring6webapp.domain.Book;
import com.sfg.course.spring6webapp.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
