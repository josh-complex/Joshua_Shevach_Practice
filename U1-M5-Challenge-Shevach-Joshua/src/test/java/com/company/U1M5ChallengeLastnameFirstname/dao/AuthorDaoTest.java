package com.company.U1M5ChallengeLastnameFirstname.dao;

import com.company.U1M5ChallengeLastnameFirstname.model.Author;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AuthorDaoTest {

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
    public void shouldCreateFindDeleteAuthor() {
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

        assertEquals(author, authorDao.findAuthor(author.getId()));

        authorDao.deleteAuthor(author.getId());

        assertNull(authorDao.findAuthor(author.getId()));
    }

    @Test
    public void shouldFindAllAuthors() {
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

        assertEquals(1, authorDao.findAllAuthors().size());

        Author author2 = authorDao.createAuthor(new Author(
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

        assertEquals(2, authorDao.findAllAuthors().size());

        authorDao.deleteAuthor(author.getId());

        assertEquals(1, authorDao.findAllAuthors().size());
    }

    @Test
    public void shouldUpdateAuthor() {
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

        author.setState("CA");
        author.setFirstName("Blank");

        authorDao.updateAuthor(author);

        assertEquals("Blank", authorDao.findAuthor(author.getId()).getFirstName());
    }

}
