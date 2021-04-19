package com.company.booksystemservice.service;

import com.company.booksystemservice.dao.BookDao;
import com.company.booksystemservice.feign.NoteClient;
import com.company.booksystemservice.model.Book;
import com.company.booksystemservice.util.messages.NoteEntry;
import com.company.booksystemservice.viewmodel.BookViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Mock
    private BookDao dao;

    private BookService service;

    @Mock
    private NoteClient client;

    Book inputBook1 = new Book();
    Book outputBook1 = new Book();

    Book inputBook2 = new Book();
    Book outputBook2 = new Book();

    NoteEntry inputNoteEntry1 = new NoteEntry();
    NoteEntry outputNoteEntry1 = new NoteEntry();

    NoteEntry inputNoteEntry2 = new NoteEntry();
    NoteEntry outputNoteEntry2 = new NoteEntry();

    List<NoteEntry> inputNoteEntries = new ArrayList<>();
    List<NoteEntry> noteEntries = new ArrayList<>();
    List<Book> books = new ArrayList<>();

    @Before
    public void setUp() {
        /*
         * Note mocks
         */

        inputNoteEntry1.setBookId(1);
        inputNoteEntry1.setNote("Note");
        outputNoteEntry1.setId(1);
        outputNoteEntry1.setBookId(1);
        outputNoteEntry1.setNote("Note");

        inputNoteEntry2.setId(2);
        inputNoteEntry2.setBookId(1);
        inputNoteEntry2.setNote("Note");
        outputNoteEntry2.setId(2);
        outputNoteEntry2.setBookId(1);
        outputNoteEntry2.setNote("Note");

        noteEntries.add(outputNoteEntry1);
        noteEntries.add(outputNoteEntry2);

        inputNoteEntries.add(inputNoteEntry1);
        inputNoteEntries.add(inputNoteEntry2);

        when(client.createNote(inputNoteEntry1)).thenReturn(outputNoteEntry1);
        doNothing().when(client).updateNote(inputNoteEntry2);
        /*when(client.getNote(1)).thenReturn(outputNoteEntry1);
        when(client.getNotes(null)).thenReturn(noteEntries);*/
        when(client.getNotes(1)).thenReturn(noteEntries);

        /*
         * Book mocks
         */

        inputBook1.setAuthor("George R.R. Martin");
        inputBook1.setTitle("Song of Ice and Fire");
        outputBook1.setId(1);
        outputBook1.setAuthor("George R.R. Martin");
        outputBook1.setTitle("Song of Ice and Fire");

        inputBook2.setAuthor("J.k. Rowling");
        inputBook2.setTitle("Fantastic Beast and Where to Find Them");
        outputBook2.setId(2);
        outputBook2.setAuthor("J.k. Rowling");
        outputBook2.setTitle("Fantastic Beast and Where to Find Them");

        books.add(outputBook1);
        books.add(outputBook2);

        when(dao.createBook(inputBook1)).thenReturn(outputBook1);
        when(dao.getBook(1)).thenReturn(outputBook1);
        when(dao.getAllBooks()).thenReturn(books);

        service = new BookService(client, dao);

    }

    @Test
    public void shouldCreateBookAndReturnBookViewModelThenGetSameBookViewModelFromService() {

        BookViewModel inputBookViewModel = new BookViewModel(
                null,
                "Song of Ice and Fire",
                "George R.R. Martin",
                inputNoteEntries
        );

        BookViewModel book = service.createBook(inputBookViewModel);
        BookViewModel bookGottenFromService = service.getBook(1);
        List<BookViewModel> booksGottenFromService = service.getAllBooks();

        assertEquals(book, bookGottenFromService);
        assertEquals(2, book.getNoteEntries().size());
        assertNotNull(book.getNoteEntries().get(0).getId());
        assertEquals(2, booksGottenFromService.size());

        verify(client, times(3)).getNotes(1);
        verify(client, times(1)).createNote(any(NoteEntry.class));
        verify(client, times(1)).updateNote(any(NoteEntry.class));
        verify(client, times(1)).getNotes(2);

    }

}
