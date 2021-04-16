package com.company.booksystemservice.dao;

import com.company.booksystemservice.model.Book;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {

    @Autowired
    BookDao dao;

    @After
    public void setUp() {
        dao.getAllBooks().forEach(x -> dao.deleteBook(x.getId()));
    }

    @Test
    public void shouldAddGetDeleteBook() {
        Book book = new Book();
        book.setTitle("Song of Ice and Fire");
        book.setAuthor("George R.R. Martin");

        book = dao.createBook(book);

        Book book2 = dao.getBook(book.getId());

        assertEquals("Book gotten from dao must match placeholder book", book, book2);

        dao.deleteBook(book.getId());

        assertNull("Dao must not have reference to book after deletion", dao.getBook(book.getId()));
    }

    @Test
    public void shouldGetAllBooksFromDao() {
        Book book = new Book();
        book.setTitle("Song of Ice and Fire");
        book.setAuthor("George R.R. Martin");

        dao.createBook(book);

        assertEquals("Size of books from database must match expectation", 1, dao.getAllBooks().size());
    }

    @Test
    public void shouldUpdateBook() {
        Book book = new Book();
        book.setTitle("Song of Ice and Fire");
        book.setAuthor("George R.R. Martin");

        book = dao.createBook(book);

        book.setAuthor("Georgie R.R. Martino");

        dao.updateBook(book);

        book = dao.getBook(book.getId());

        assertNotEquals("Author name must be modified", "George R.R. Martin", dao.getBook(book.getId()).getAuthor());
    }

}
