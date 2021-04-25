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
@WebMvcTest(StwitterController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class StwitterControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    StwitterService service;

    ObjectMapper mapper = new ObjectMapper();

    PostViewModel inputPost1 = new PostViewModel();
    PostViewModel outputPost1 = new PostViewModel();

    PostViewModel inputPost2 = new PostViewModel();
    PostViewModel outputPost2 = new PostViewModel();

    PostViewModel updatePost = new PostViewModel();

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

        inputPost1.setPosterName("Josh");
        inputPost1.setPostDate(LocalDate.of(2020, 6, 28));
        inputPost1.setPost("Hey, I'm making a post!!");
        inputPost1.setComments(inputComments);

        outputPost1.setPostId(1);
        outputPost1.setPosterName("Josh");
        outputPost1.setPostDate(LocalDate.of(2020, 6, 28));
        outputPost1.setPost("Hey, I'm making a post!!");
        outputPost1.setComments(outputComments);

        updatePost.setPostId(1);
        updatePost.setPosterName("Josh");
        updatePost.setPostDate(LocalDate.of(2020, 6, 28));
        updatePost.setPost("Hey, I'm making an updated post!!");
        updatePost.setComments(outputComments);

        inputPost2.setPosterName("Josh");
        inputPost2.setPostDate(LocalDate.of(2020, 6, 29));
        inputPost2.setPost("Hey, now I'm making a post without any comments!!");
        inputPost2.setComments(null);

        outputPost2.setPostId(2);
        outputPost2.setPosterName("Josh");
        outputPost2.setPostDate(LocalDate.of(2020, 6, 29));
        outputPost2.setPost("Hey, now I'm making a post without any comments!!");
        outputPost2.setComments(null);

        outputPosts.add(outputPost1);
        outputPosts.add(outputPost2);

        when(service.createPost(inputPost1)).thenReturn(outputPost1);
        when(service.findPostById(1)).thenReturn(outputPost1);
        when(service.findPosts()).thenReturn(outputPosts);
        when(service.findPosts("Josh")).thenReturn(outputPosts);
        when(service.updatePost(updatePost)).thenReturn(updatePost);

    }

    @Test
    public void shouldPassInputPostToPostEndpointAndReturnPostViewModel() throws Exception {

        String input = mapper.writeValueAsString(inputPost1);
        String output = mapper.writeValueAsString(outputPost1);

        mvc.perform(post("/stwitter")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(output));

    }

    @Test
    public void shouldReturnPostViewModelWhenFindingByIdOf1() throws Exception {

        String output = mapper.writeValueAsString(outputPost1);

        mvc.perform(get("/stwitter/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output));

    }

    @Test
    public void shouldReturnListOfAllPostViewModels() throws Exception {

        String output = mapper.writeValueAsString(outputPosts);

        mvc.perform(get("/stwitter"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output));

    }

    @Test
    public void shouldFindPostsByPosterNameParamAndReturnListOfPostViewModels() throws Exception {

        String output = mapper.writeValueAsString(outputPosts);

        mvc.perform(get("/stwitter")
                .param("posterName", "Josh"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output));

    }

    @Test
    public void shouldUpdatePostWithGivenIdAndReturnUpdatedPostViewModel() throws Exception {

        String input = mapper.writeValueAsString(updatePost);
        String output = mapper.writeValueAsString(updatePost);

        mvc.perform(put("/stwitter")
                .content(input)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(output));

    }

}
