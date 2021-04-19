package com.company.booksystemservice.controller;

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
    public BookViewModel createBook(@RequestBody BookViewModel book) {
        return service.createBook(book);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getBook(@PathVariable Integer id) {
        return service.getBook(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookViewModel> getAllBooks() {
        return service.getAllBooks();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(BookViewModel book) {
        service.updateBook(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Integer id) {
        service.deleteBook(id);
    }

}
