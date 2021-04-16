package com.company.noteservice.dao;

import com.company.noteservice.model.Note;

import java.util.List;

public interface NoteDao {

    Note createNote(Note note);
    Note getNote(Integer id);
    List<Note> getNotesByBookId(Integer id);
    List<Note> getAllNotes();
    void updateNote(Note note);
    void deleteNote(Integer id);

}
