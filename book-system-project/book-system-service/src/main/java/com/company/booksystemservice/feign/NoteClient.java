package com.company.booksystemservice.feign;

import com.company.booksystemservice.util.messages.NoteEntry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "note-service-settings")
public interface NoteClient {

    @PostMapping("/note")
    @ResponseStatus(HttpStatus.CREATED)
    NoteEntry createNote(@Valid @RequestBody NoteEntry noteEntry);

    @GetMapping("/note/{id}")
    @ResponseStatus(HttpStatus.OK)
    NoteEntry getNote(@PathVariable Integer id);

    @GetMapping("/note")
    @ResponseStatus(HttpStatus.OK)
    List<NoteEntry> getNotes(@RequestParam(required = false) Integer bookId);

    @PutMapping("/note")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateNote(@Valid @RequestBody NoteEntry noteEntry);

    @DeleteMapping("/note/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteNote(@PathVariable Integer id);

}