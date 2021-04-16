package com.company.booksystemservice.controller;

import com.company.booksystemservice.model.Book;
import com.company.booksystemservice.service.BookService;
import com.company.booksystemservice.util.messages.NoteEntry;
import com.company.booksystemservice.viewmodel.BookViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService service;

    private final ObjectMapper mapper = new ObjectMapper();

    Book inputBook1 = new Book();
    BookViewModel outputBook1 = new BookViewModel();

    Book inputBook2 = new Book();
    BookViewModel outputBook2 = new BookViewModel();

    NoteEntry inputNoteEntry1 = new NoteEntry();
    NoteEntry outputNoteEntry1 = new NoteEntry();

    NoteEntry inputNoteEntry2 = new NoteEntry();
    NoteEntry outputNoteEntry2 = new NoteEntry();

    List<NoteEntry> noteEntries = new ArrayList<>();
    List<BookViewModel> books = new ArrayList<>();

    @Before
    public void setUp() {
        /*
         * Note mocks
         */

        /*inputNoteEntry1.setBookId(1);
        inputNoteEntry1.setNote("Note");
        outputNoteEntry1.setId(1);
        outputNoteEntry1.setBookId(1);
        outputNoteEntry1.setNote("Note");

        inputNoteEntry2.setBookId(1);
        inputNoteEntry2.setNote("Note");
        outputNoteEntry2.setId(2);
        outputNoteEntry2.setBookId(1);
        outputNoteEntry2.setNote("Note");

        noteEntries.add(outputNoteEntry1);
        noteEntries.add(outputNoteEntry2);

        when(client.createNote(inputNoteEntry1)).thenReturn(outputNoteEntry1);
        when(client.getNote(1)).thenReturn(outputNoteEntry1);
        when(client.getNotes(null)).thenReturn(noteEntries);
        when(client.getNotes(1)).thenReturn(noteEntries);*/

        /*
         * Book mocks
         */

        inputBook1.setAuthor("George R.R. Martin");
        inputBook1.setTitle("Song of Ice and Fire");
        outputBook1.setId(1);
        outputBook1.setAuthor("George R.R. Martin");
        outputBook1.setTitle("Song of Ice and Fire");
        outputBook1.setNoteEntries(noteEntries);

        inputBook2.setAuthor("J.k. Rowling");
        inputBook2.setTitle("Fantastic Beast and Where to Find Them");
        outputBook2.setId(2);
        outputBook2.setAuthor("J.k. Rowling");
        outputBook2.setTitle("Fantastic Beast and Where to Find Them");
        outputBook2.setNoteEntries(noteEntries);

        books.add(outputBook1);
        books.add(outputBook2);

        when(service.createBook(inputBook1)).thenReturn(outputBook1);
        when(service.getBook(1)).thenReturn(outputBook1);
        when(service.getAllBooks()).thenReturn(books);
    }

    @Test
    public void shouldAddBookAndReturnBookViewModel() throws Exception {
        String inputJson = mapper.writeValueAsString(inputBook1);
        String outputJson = mapper.writeValueAsString(outputBook1);

        mvc.perform(post("/book")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

}
