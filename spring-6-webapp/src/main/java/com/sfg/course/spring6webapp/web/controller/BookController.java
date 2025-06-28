package com.sfg.course.spring6webapp.web.controller;

import com.sfg.course.spring6webapp.model.Book;
import lombok.RequiredArgsConstructor;

import java.util.List;

import com.sfg.course.spring6webapp.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;

    @GetMapping("/sfgv6/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.findAll()
                .stream()
                .map(bookEntity -> new Book(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getIsbn()))
                .toList();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
