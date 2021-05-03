package com.trilogyed.stwitterservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.stwitterservice.model.Comment;
import com.trilogyed.stwitterservice.service.StwitterService;
import com.trilogyed.stwitterservice.viewmodel.PostViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StwitterCommentController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class StwitterCommentControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    StwitterService service;

    ObjectMapper mapper = new ObjectMapper();

    PostViewModel outputPost1 = new PostViewModel();
    PostViewModel outputPost2 = new PostViewModel();

    Comment updateComment = new Comment();
    Comment updateCommentNotFound = new Comment();

    Comment inputComment1 = new Comment();
    Comment outputComment1 = new Comment();

    Comment inputComment2 = new Comment();
    Comment outputComment2 = new Comment();

    List<Comment> inputComments = new ArrayList<>();
    List<Comment> outputComments = new ArrayList<>();

    List<PostViewModel> outputPosts = new ArrayList<>();

    @Before
    public void setUp() {

        inputComment1.setCommenterName("AJR");
        inputComment1.setCreateDate(LocalDate.of(2020, 6, 28));
        inputComment1.setComment("Hey, I'm commenting on your post!!");

        outputComment1.setCommentId(1);
        outputComment1.setPostId(1);
        outputComment1.setCommenterName("AJR");
        outputComment1.setCreateDate(LocalDate.of(2020, 6, 28));
        outputComment1.setComment("Hey, I'm commenting on your post!!");

        inputComment2.setCommenterName("AJR");
        inputComment2.setCreateDate(LocalDate.of(2020, 6, 29));
        inputComment2.setComment("Hey, I'm making another comment on your post!!");

        outputComment2.setCommentId(2);
        outputComment2.setPostId(1);
        outputComment2.setCommenterName("AJR");
        outputComment2.setCreateDate(LocalDate.of(2020, 6, 29));
        outputComment2.setComment("Hey, I'm making another comment on your post!!");

        inputComments.add(inputComment1);
        inputComments.add(inputComment2);
        outputComments.add(outputComment1);
        outputComments.add(outputComment2);

        outputPost1.setPostId(1);
        outputPost1.setPosterName("Josh");
        outputPost1.setPostDate(LocalDate.of(2020, 6, 28));
        outputPost1.setPost("Hey, I'm making a post!!");
        outputPost1.setComments(outputComments);

        updateComment.setPostId(1);
        updateComment.setCommenterName("AJR");
        updateComment.setCreateDate(LocalDate.of(2020, 6, 29));
        updateComment.setComment("Hey, I'm updating my comment on your post!!");

        updateCommentNotFound.setCommenterName("AJR");
        updateCommentNotFound.setCreateDate(LocalDate.of(2020, 6, 29));
        updateCommentNotFound.setComment("Hey, I'm updating my comment on your post!!");

        when(service.createComment(inputComment1)).thenReturn(outputPost1);
        when(service.findPostById(1)).thenReturn(outputPost1);
        when(service.updateComment(updateComment)).thenReturn(outputPost1);

    }

    @Test
    public void shouldCreateCommentAndReturnRelatedPostViewModel() throws Exception {

        String input = mapper.writeValueAsString(inputComment1);
        String output = mapper.writeValueAsString(outputPost1);

        mvc.perform(post("/stwitter/comment")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(output));

    }

    @Test
    public void shouldUpdateCommentAndReturnRelatedPostViewModel() throws Exception {

        String input = mapper.writeValueAsString(updateComment);
        String output = mapper.writeValueAsString(outputPost1);

        mvc.perform(put("/stwitter/comment")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output));

    }

    @Test
    public void shouldUpdateCommentAndThrowNotFoundException() throws Exception {

        String input = mapper.writeValueAsString(updateCommentNotFound);

        mvc.perform(put("/stwitter/comment")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
