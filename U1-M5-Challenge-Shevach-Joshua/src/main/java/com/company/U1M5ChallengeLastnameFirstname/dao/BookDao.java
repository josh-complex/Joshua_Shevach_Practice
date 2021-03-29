package com.company.U1M5ChallengeLastnameFirstname.dao;

import com.company.U1M5ChallengeLastnameFirstname.model.Author;
import com.company.U1M5ChallengeLastnameFirstname.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

public interface BookDao {

    Book findBook(int id);
    List<Book> findBooksByAuthor(Author author);
    Book createBook(Book book);
    List<Book> findAllBooks();
    void updateBook(Book book);
    void deleteBook(int id);

}
