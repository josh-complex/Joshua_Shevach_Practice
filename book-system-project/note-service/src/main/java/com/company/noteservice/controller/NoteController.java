package com.company.noteservice.controller;

import com.company.noteservice.dao.NoteDao;
import com.company.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/note")
@RefreshScope
public class NoteController {

    NoteDao dao;

    @Autowired
    public NoteController(NoteDao dao) {
        this.dao = dao;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@Valid @RequestBody Note note) {
        return dao.createNote(note);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Note getNote(@PathVariable Integer id) {
        return dao.getNote(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getNotes(@RequestParam(required = false) Integer bookId) {
        // condition (? check) true : false;
        return bookId == null ? dao.getAllNotes() : dao.getNotesByBookId(bookId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNote(@Valid @RequestBody Note note) {
        dao.updateNote(note);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable Integer id) {
        dao.deleteNote(id);
    }

}
