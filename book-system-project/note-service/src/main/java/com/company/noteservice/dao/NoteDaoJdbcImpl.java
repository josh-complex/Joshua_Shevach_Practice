package com.company.noteservice.dao;

import com.company.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NoteDaoJdbcImpl implements NoteDao {

    private static final String INSERT_NOTE_SQL =
            "insert into note (book_id, note) values (?, ?)";

    private static final String SELECT_NOTE_SQL =
            "select * from note where note_id = ?";

    private static final String SELECT_ALL_NOTES_BY_BOOK_ID_SQL =
            "select * from note where book_id = ?";
    
    private static final String SELECT_ALL_NOTES_SQL =
            "select * from note";

    private static final String DELETE_NOTE_SQL =
            "delete from note where note_id = ?";

    private static final String UPDATE_NOTE_SQL =
            "update note set book_id = ?, note = ? where note_id = ?";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public NoteDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Note createNote(Note note) {
        jdbcTemplate.update(
                INSERT_NOTE_SQL,
                note.getBookId(),
                note.getNote()
        );

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        note.setId(id);

        return note;
    }

    @Override
    public Note getNote(Integer id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_NOTE_SQL, this::mapRowToNote, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Note> getNotesByBookId(Integer id) {
        return jdbcTemplate.query(SELECT_ALL_NOTES_BY_BOOK_ID_SQL, this::mapRowToNote, id);
    }

    @Override
    public List<Note> getAllNotes() {
        return jdbcTemplate.query(SELECT_ALL_NOTES_SQL, this::mapRowToNote);
    }

    @Override
    public void updateNote(Note note) {
        jdbcTemplate.update(
                UPDATE_NOTE_SQL,
                note.getBookId(),
                note.getNote(),
                note.getId()
        );
    }

    @Override
    public void deleteNote(Integer id) {
        jdbcTemplate.update(DELETE_NOTE_SQL, id);
    }

    private Note mapRowToNote(ResultSet rs, int rowNum) throws SQLException {
        return new Note(
                rs.getInt("note_id"),
                rs.getInt("book_id"),
                rs.getString("note")
        );
    }
}
