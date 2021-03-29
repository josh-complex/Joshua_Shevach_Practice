package com.company.U1M5ChallengeLastnameFirstname.dao;

import com.company.U1M5ChallengeLastnameFirstname.model.Author;
import com.company.U1M5ChallengeLastnameFirstname.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class BookDaoJdbcImpl implements BookDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_BOOK_SQL =
            "insert into book (isbn, publish_date, author_id, title, publisher_id, price) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BOOK_SQL =
            "select * from book where book_id = ?";

    private static final String SELECT_BOOKS_BY_AUTHOR_SQL =
            "select * from book where author_id = ?";

    private static final String SELECT_ALL_BOOKS_SQL =
            "select * from book";

    private static final String UPDATE_BOOK_SQL =
            "update book set isbn = ?, publish_date = ?, author_id = ?, title = ?, publisher_id = ?, price = ? where book_id = ?";

    private static final String DELETE_BOOK_SQL =
            "delete from book where book_id = ?";

    @Autowired
    public BookDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book findBook(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Book createBook(Book Book) {
        jdbcTemplate.update(
                INSERT_BOOK_SQL,
                Book.getIsbn(),
                Book.getPublishDate(),
                Book.getAuthorId(),
                Book.getTitle(),
                Book.getPublisherId(),
                Book.getPrice()
        );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        Book.setId(id);

        return Book;
    }

    @Override
    public List<Book> findAllBooks() {
        return jdbcTemplate.query(SELECT_ALL_BOOKS_SQL, this::mapRowToBook);
    }

    @Override
    public List<Book> findBooksByAuthor(Author author) {
        return jdbcTemplate.query(SELECT_BOOKS_BY_AUTHOR_SQL, this::mapRowToBook, author.getId());
    }

    @Override
    public void updateBook(Book Book) {
        jdbcTemplate.update(
                UPDATE_BOOK_SQL,
                Book.getIsbn(),
                Book.getPublishDate(),
                Book.getAuthorId(),
                Book.getTitle(),
                Book.getPublisherId(),
                Book.getPrice(),
                Book.getId()
        );
    }

    @Override
    public void deleteBook(int id) {
        jdbcTemplate.update(DELETE_BOOK_SQL, id);
    }

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {

        return new Book(
                rs.getInt("book_id"),
                rs.getString("isbn"),
                rs.getDate("publish_date").toLocalDate(),
                rs.getInt("author_id"),
                rs.getString("title"),
                rs.getInt("publisher_id"),
                rs.getBigDecimal("price")
        );

    }
}
