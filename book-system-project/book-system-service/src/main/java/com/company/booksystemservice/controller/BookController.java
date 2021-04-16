package com.company.booksystemservice.controller;

import com.company.booksystemservice.model.Book;
import com.company.booksystemservice.service.BookService;
import com.company.booksystemservice.viewmodel.BookViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RefreshScope
public class BookController {

    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.controller";

    private final RabbitTemplate rabbitTemplate;
    private final BookService service;

    @Autowired
    public BookController(RabbitTemplate rabbitTemplate, BookService service) {
        this.rabbitTemplate = rabbitTemplate;
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookViewModel createBook(@RequestBody Book book) {
        return null;
    }

    @GetMapping("/{id}")
    public BookViewModel getBook(@PathVariable Integer id) {
        return null;
    }

    @GetMapping
    public List<BookViewModel> getAllBooks() {
        return null;
    }

    @PutMapping
    public void updateBook(Book book) {

    }

    @DeleteMapping
    public void deleteBook(Integer id) {

    }

}
