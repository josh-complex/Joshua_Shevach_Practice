package com.company.booksystemservice.service;

import com.company.booksystemservice.dao.BookDao;
import com.company.booksystemservice.feign.NoteClient;
import com.company.booksystemservice.model.Book;
import com.company.booksystemservice.viewmodel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    BookDao dao;
    NoteClient client;

    @Autowired
    public BookService(NoteClient client, BookDao dao) {
        this.client = client;
        this.dao = dao;
    }

    public BookViewModel createBook(Book book) {
        return buildBookViewModelFromBook(dao.createBook(book));
    }

    public BookViewModel getBook(Integer id) {
        return buildBookViewModelFromBook(dao.getBook(id));
    }

    public List<BookViewModel> getAllBooks() {
        List<BookViewModel> vms = new ArrayList<>();
        List<Book> books = dao.getAllBooks();
        for(Book book: books) {
            vms.add(buildBookViewModelFromBook(book));
        }
        return vms;
    }

    public void updateBook(Book book) {
        dao.updateBook(book);
    }

    public void deleteBook(Integer id) {
        dao.deleteBook(id);
    }

    public BookViewModel buildBookViewModelFromBook(Book book) {
        return new BookViewModel(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                client.getNotes(book.getId())
        );
    }

}
