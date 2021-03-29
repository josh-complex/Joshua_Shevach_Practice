package com.company.U1M5ChallengeLastnameFirstname.dao;

import com.company.U1M5ChallengeLastnameFirstname.model.Author;
import com.company.U1M5ChallengeLastnameFirstname.model.Book;
import com.company.U1M5ChallengeLastnameFirstname.model.Publisher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private PublisherDao publisherDao;

    @Before
    public void setUp() throws Exception {
        bookDao.findAllBooks()
                .forEach(x -> bookDao.deleteBook(x.getId()));
        authorDao.findAllAuthors()
                .forEach(x -> authorDao.deleteAuthor(x.getId()));
        publisherDao.findAllPublishers()
                .forEach(x -> publisherDao.deletePublisher(x.getId()));
    }

    @Test
    public void shouldCreateFindDeleteBook() {
        Author author = authorDao.createAuthor(new Author(
                0,
                "George",
                "Martin",
                "111 Blue Lane",
                "Florida",
                "FL",
                "22121",
                "8675309",
                "someemail@somerandomdomain.com"
        ));

        Publisher publisher = publisherDao.createPublisher(new Publisher(
                0,
                "Google",
                "111 Blue Lane",
                "Florida",
                "FL",
                "22121",
                "8675309",
                "someemail@somerandomdomain.com"
        ));

        Book book = bookDao.createBook(new Book(
                0,
                "123k2j",
                LocalDate.of(2020, 9, 20),
                author.getId(),
                "Fable Label",
                publisher.getId(),
                new BigDecimal("22.95")
        ));

        assertEquals(book, bookDao.findBook(book.getId()));

        bookDao.deleteBook(book.getId());

        assertNull(bookDao.findBook(book.getId()));
    }

    @Test
    public void shouldFindAllBooks() {
        Author author = authorDao.createAuthor(new Author(
                0,
                "George",
                "Martin",
                "111 Blue Lane",
                "Florida",
                "FL",
                "22121",
                "8675309",
                "someemail@somerandomdomain.com"
        ));

        Publisher publisher = publisherDao.createPublisher(new Publisher(
                0,
                "Google",
                "111 Blue Lane",
                "Florida",
                "FL",
                "22121",
                "8675309",
                "someemail@somerandomdomain.com"
        ));

        Book book = bookDao.createBook(new Book(
                0,
                "123k2j",
                LocalDate.of(2020, 9, 20),
                author.getId(),
                "Fable Label",
                publisher.getId(),
                new BigDecimal("22.95")
        ));

        Book book2 = bookDao.createBook(new Book(
                0,
                "2929jf",
                LocalDate.of(2021, 1, 22),
                author.getId(),
                "Stable Fable Label",
                publisher.getId(),
                new BigDecimal("32.95")
        ));

        assertEquals(2, bookDao.findAllBooks().size());
    }

    @Test
    public void shouldFindBooksByAuthor() {
        Author author = authorDao.createAuthor(new Author(
                0,
                "George",
                "Martin",
                "111 Blue Lane",
                "Florida",
                "FL",
                "22121",
                "8675309",
                "someemail@somerandomdomain.com"
        ));

        Publisher publisher = publisherDao.createPublisher(new Publisher(
                0,
                "Google",
                "111 Blue Lane",
                "Florida",
                "FL",
                "22121",
                "8675309",
                "someemail@somerandomdomain.com"
        ));

        Book book = bookDao.createBook(new Book(
                0,
                "123k2j",
                LocalDate.of(2020, 9, 20),
                author.getId(),
                "Fable Label",
                publisher.getId(),
                new BigDecimal("22.95")
        ));

        Book book2 = bookDao.createBook(new Book(
                0,
                "2929jf",
                LocalDate.of(2021, 1, 22),
                author.getId(),
                "Stable Fable Label",
                publisher.getId(),
                new BigDecimal("32.95")
        ));

        assertEquals(2, bookDao.findBooksByAuthor(author).size());
    }

    @Test
    public void shouldUpdateBook() {
        Author author = authorDao.createAuthor(new Author(
                0,
                "George",
                "Martin",
                "111 Blue Lane",
                "Florida",
                "FL",
                "22121",
                "8675309",
                "someemail@somerandomdomain.com"
        ));

        Publisher publisher = publisherDao.createPublisher(new Publisher(
                0,
                "Google",
                "111 Blue Lane",
                "Florida",
                "FL",
                "22121",
                "8675309",
                "someemail@somerandomdomain.com"
        ));

        Book book = bookDao.createBook(new Book(
                0,
                "123k2j",
                LocalDate.of(2020, 9, 20),
                author.getId(),
                "Fable Label",
                publisher.getId(),
                new BigDecimal("22.95")
        ));

        book.setTitle("Stable Fable Label");

        bookDao.updateBook(book);

        assertEquals("Stable Fable Label", bookDao.findBook(book.getId()).getTitle());
    }

}
