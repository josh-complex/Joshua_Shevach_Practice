package com.company.booksystemservice.dao;

import com.company.booksystemservice.model.Book;

import java.util.List;

public interface BookDao {

    Book createBook(Book book);
    Book getBook(Integer id);
    List<Book> getAllBooks();
    void updateBook(Book book);
    void deleteBook(Integer id);

}
