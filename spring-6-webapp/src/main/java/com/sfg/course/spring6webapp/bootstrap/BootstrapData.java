package com.sfg.course.spring6webapp.bootstrap;

import lombok.RequiredArgsConstructor;

import com.sfg.course.spring6webapp.entity.AuthorEntity;
import com.sfg.course.spring6webapp.entity.BookEntity;
import com.sfg.course.spring6webapp.entity.PublisherEntity;
import com.sfg.course.spring6webapp.repository.AuthorRepository;
import com.sfg.course.spring6webapp.repository.BookRepository;
import com.sfg.course.spring6webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public void run(String... args) {
        AuthorEntity eric = new AuthorEntity();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        BookEntity ddd = new BookEntity();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        AuthorEntity ericSaved = authorRepository.save(eric);
        BookEntity dddSaved = bookRepository.save(ddd);

        AuthorEntity rod = new AuthorEntity();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        BookEntity noEJB = new BookEntity();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        AuthorEntity rodSaved = authorRepository.save(rod);
        BookEntity noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);


        PublisherEntity publisherEntity = new PublisherEntity();
        publisherEntity.setPublisherName("My Publisher");
        publisherEntity.setAddress("123 Main");
        PublisherEntity savedPublisherEntity = publisherRepository.save(publisherEntity);

        dddSaved.setPublisher(savedPublisherEntity);
        noEJBSaved.setPublisher(savedPublisherEntity);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}










