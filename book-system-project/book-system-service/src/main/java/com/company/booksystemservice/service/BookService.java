package com.company.booksystemservice.service;

import com.company.booksystemservice.dao.BookDao;
import com.company.booksystemservice.feign.NoteClient;
import com.company.booksystemservice.model.Book;
import com.company.booksystemservice.viewmodel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public BookViewModel createBook(BookViewModel book) {
        Book bookFromDao = dao.createBook(buildBookFromBookViewModel(book));

        if(book.getNoteEntries() != null) {
            book.getNoteEntries().forEach(x -> {
                x.setBookId(bookFromDao.getId());

                if(x.getId() == null) {
                    client.createNote(x);
                } else {
                    client.updateNote(x);
                    client.getNote(x.getId());
                }

            });
        }

        return buildBookViewModelFromBook(bookFromDao);
    }

    @Transactional
    public BookViewModel getBook(Integer id) {
        return buildBookViewModelFromBook(dao.getBook(id));
    }

    @Transactional
    public List<BookViewModel> getAllBooks() {
        List<BookViewModel> vms = new ArrayList<>();
        List<Book> books = dao.getAllBooks();
        for(Book book: books) {
            vms.add(buildBookViewModelFromBook(book));
        }
        return vms;
    }

    @Transactional
    public void updateBook(BookViewModel book) {
        dao.updateBook(buildBookFromBookViewModel(book));
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

    public Book buildBookFromBookViewModel(BookViewModel vm) {
        return new Book(
                vm.getId(),
                vm.getTitle(),
                vm.getAuthor()
        );
    }

}
