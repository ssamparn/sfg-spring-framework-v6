package com.sfg.course.spring6webapp.web.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import com.sfg.course.spring6webapp.domain.Author;
import com.sfg.course.spring6webapp.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/sfgv6/authors")
    public ResponseEntity<List<Author>> getAuthors() {
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
    }
}
