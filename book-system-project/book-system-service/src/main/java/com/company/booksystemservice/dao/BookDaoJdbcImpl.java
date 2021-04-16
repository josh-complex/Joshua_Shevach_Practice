package com.company.booksystemservice.dao;

import com.company.booksystemservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbcImpl implements BookDao {

    private static final String INSERT_BOOK_SQL =
            "insert into book (title, author) values (?, ?)";

    private static final String SELECT_BOOK_SQL =
            "select * from book where book_id = ?";

    private static final String SELECT_ALL_BOOKS_SQL =
            "select * from book";

    private static final String DELETE_BOOK_SQL =
            "delete from book where book_id = ?";

    private static final String UPDATE_BOOK_SQL =
            "update book set title = ?, author = ? where book_id = ?";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Book createBook(Book book) {
        jdbcTemplate.update(
                INSERT_BOOK_SQL,
                book.getTitle(),
                book.getAuthor()
        );

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        book.setId(id);

        return book;
    }

    @Override
    public Book getBook(Integer id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(SELECT_ALL_BOOKS_SQL, this::mapRowToBook);
    }

    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update(
                UPDATE_BOOK_SQL,
                book.getTitle(),
                book.getAuthor(),
                book.getId()
        );
    }

    @Override
    public void deleteBook(Integer id) {
        jdbcTemplate.update(DELETE_BOOK_SQL, id);
    }

    public Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        return new Book(
          rs.getInt("book_id"),
          rs.getString("title"),
          rs.getString("author")
        );
    }
}
