package com.company.noteservice.controller;

import com.company.noteservice.dao.NoteDao;
import com.company.noteservice.model.Note;

import com.fasterxml.jackson.databind.json.JsonMapper;

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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class NoteControllerTest {

    @MockBean
    NoteDao dao;

    @Autowired
    MockMvc mockMvc;

    JsonMapper mapper = new JsonMapper();

    Note inputNote1 = new Note();
    Note outputNote1 = new Note();
    Note outputNote2 = new Note();
    List<Note> outputNotes;

    @Before
    public void setUp() {
        inputNote1.setBookId(1);
        inputNote1.setNote("Some Note");

        outputNote1.setId(1);
        outputNote1.setBookId(1);
        outputNote1.setNote("Some Note");

        outputNote2.setId(2);
        outputNote2.setBookId(1);
        outputNote2.setNote("Some Note");

        outputNotes = new ArrayList<Note>() {{
            add(outputNote1);
            add(outputNote2);
        }};

        when(dao.createNote(inputNote1)).thenReturn(outputNote1);
        when(dao.getNote(1)).thenReturn(outputNote1);
        when(dao.getAllNotes()).thenReturn(outputNotes);
        when(dao.getNotesByBookId(1)).thenReturn(outputNotes);
    }

    @Test
    public void shouldAddNote() throws Exception {

        String inputJson = mapper.writeValueAsString(inputNote1);
        String outputJson = mapper.writeValueAsString(outputNote1);

        mockMvc.perform(post("/note")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetNoteById() throws Exception {

        String outputJson = mapper.writeValueAsString(outputNote1);

        mockMvc.perform(get("/note/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllNotes() throws Exception {

        String outputJson = mapper.writeValueAsString(outputNotes);

        mockMvc.perform(get("/note"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllNotesByBookId() throws Exception {

        String outputJson = mapper.writeValueAsString(outputNotes);

        mockMvc.perform(get("/note")
                .param("bookId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldUpdateAndCallDaoUpdateMethod1Time() throws Exception {

        String inputJson = mapper.writeValueAsString(inputNote1);

        mockMvc.perform(put("/note")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(dao, times(1)).updateNote(inputNote1);

    }

    @Test
    public void shouldDeleteAndCallDaoDeleteMethod1Time() throws Exception {

        mockMvc.perform(delete("/note/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(dao, times(1)).deleteNote(1);

    }

}
