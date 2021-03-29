package com.company.U1M5ChallengeLastnameFirstname.dao;

import com.company.U1M5ChallengeLastnameFirstname.model.Publisher;
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
public class PublisherDaoTest {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private PublisherDao authorDao;
    @Autowired
    private PublisherDao publisherDao;

    @Before
    public void setUp() throws Exception {
        bookDao.findAllBooks()
                .forEach(x -> bookDao.deleteBook(x.getId()));
        authorDao.findAllPublishers()
                .forEach(x -> authorDao.deletePublisher(x.getId()));
        publisherDao.findAllPublishers()
                .forEach(x -> publisherDao.deletePublisher(x.getId()));
    }

    @Test
    public void shouldCreateFindDeletePublisher() {
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

        assertEquals(publisher, publisherDao.findPublisher(publisher.getId()));

        publisherDao.deletePublisher(publisher.getId());

        assertNull(publisherDao.findPublisher(publisher.getId()));
    }

    @Test
    public void shouldFindAllPublishers() {
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

        assertEquals(1, publisherDao.findAllPublishers().size());

        Publisher publisher2 = publisherDao.createPublisher(new Publisher(
                0,
                "Google",
                "111 Blue Lane",
                "Florida",
                "FL",
                "22121",
                "8675309",
                "someemail@somerandomdomain.com"
        ));

        assertEquals(2, publisherDao.findAllPublishers().size());

        publisherDao.deletePublisher(publisher.getId());

        assertEquals(1, publisherDao.findAllPublishers().size());
    }

    @Test
    public void shouldUpdatePublisher() {
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

        publisher.setState("CA");
        publisher.setName("Amazon");

        publisherDao.updatePublisher(publisher);

        assertEquals("Amazon", publisherDao.findPublisher(publisher.getId()).getName());
    }

}
