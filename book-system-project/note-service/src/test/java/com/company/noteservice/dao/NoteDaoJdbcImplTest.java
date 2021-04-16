package com.company.noteservice.dao;

import com.company.noteservice.model.Note;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteDaoJdbcImplTest {

    @Autowired
    NoteDao dao;

    @After
    public void tearDown() {
        dao.getAllNotes().forEach(x -> dao.deleteNote(x.getId()));
    }

    @Test
    public void shouldAddGetDeleteNote() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("Hey, here's a note");

        note = dao.createNote(note);

        assertEquals("Note gotten from dao must match placeholder note", note, dao.getNote(note.getId()));

        dao.deleteNote(note.getId());

        assertNull("Dao must not have reference to note after deletion", dao.getNote(note.getId()));
    }

    @Test
    public void shouldGetNotesByBookId() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("Hey, here's a note");

        dao.createNote(note);

        Note note2 = new Note();
        note2.setBookId(1);
        note2.setNote("Hey, here's another note for the same book");

        dao.createNote(note2);

        assertEquals("Size of notes from database must match expectation", 2, dao.getNotesByBookId(1).size());
    }

    @Test
    public void shouldGetAllNotes() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("Hey, here's a note");

        dao.createNote(note);

        Note note2 = new Note();
        note2.setBookId(1);
        note2.setNote("Hey, here's another note for the same book");

        dao.createNote(note2);

        assertEquals("Size of notes from database must match expectation", 2, dao.getAllNotes().size());
    }

    @Test
    public void shouldUpdateNote() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("Hey, here's a note");

        note = dao.createNote(note);

        note.setNote("Stop changing my notes");

        dao.updateNote(note);

        note = dao.getNote(note.getId());

        assertNotEquals("Note must be modified", "Hey, here's a note", note.getNote());
    }
}