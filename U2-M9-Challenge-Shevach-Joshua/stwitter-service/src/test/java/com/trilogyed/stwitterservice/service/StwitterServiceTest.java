package com.trilogyed.stwitterservice.service;

import com.trilogyed.stwitterservice.feign.CommentClient;
import com.trilogyed.stwitterservice.feign.PostClient;
import com.trilogyed.stwitterservice.model.Comment;
import com.trilogyed.stwitterservice.model.Post;
import com.trilogyed.stwitterservice.viewmodel.PostViewModel;
import lombok.var;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StwitterServiceTest {

    public static final String TOPIC_EXCHANGE_NAME = "comment-exchange";
    public static final String ROUTING_KEY = "comment.create.stwitter.service";

    @Autowired
    StwitterService service;
    @MockBean
    RabbitTemplate commentTemplate;
    @MockBean
    PostClient postClient;
    @MockBean
    CommentClient commentClient;

    PostViewModel inputPostViewModel1 = new PostViewModel();
    Post inputPost1 = new Post();
    PostViewModel outputPostViewModel1 = new PostViewModel();
    Post outputPost1 = new Post();

    Post updatePost = new Post();

    PostViewModel inputPostViewModel2 = new PostViewModel();
    Post inputPost2 = new Post();
    PostViewModel outputPostViewModel2 = new PostViewModel();
    Post outputPost2 = new Post();

    List<Post> inputPosts = new ArrayList<>();
    List<Post> outputPosts = new ArrayList<>();

    Comment inputComment1 = new Comment();
    Comment outputComment1 = new Comment();

    Comment inputComment2 = new Comment();
    Comment outputComment2 = new Comment();

    List<Comment> inputComments = new ArrayList<>();
    List<Comment> outputComments = new ArrayList<>();

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

        inputPostViewModel1.setPosterName("Josh");
        inputPostViewModel1.setPostDate(LocalDate.of(2020, 6, 28));
        inputPostViewModel1.setPost("Hey, I'm making a post!!");
        inputPostViewModel1.setComments(inputComments);

        inputPost1.setPosterName("Josh");
        inputPost1.setPostDate(LocalDate.of(2020, 6, 28));
        inputPost1.setPost("Hey, I'm making a post!!");

        outputPostViewModel1.setPostId(1);
        outputPostViewModel1.setPosterName("Josh");
        outputPostViewModel1.setPostDate(LocalDate.of(2020, 6, 28));
        outputPostViewModel1.setPost("Hey, I'm making a post!!");
        outputPostViewModel1.setComments(outputComments);

        outputPost1.setPostId(1);
        outputPost1.setPosterName("Josh");
        outputPost1.setPostDate(LocalDate.of(2020, 6, 28));
        outputPost1.setPost("Hey, I'm making a post!!");

        updatePost.setPostId(1);
        updatePost.setPosterName("Josh");
        updatePost.setPostDate(LocalDate.of(2020, 6, 28));
        updatePost.setPost("Hey, I'm making an updated post!!");

        inputPostViewModel2.setPosterName("Josh");
        inputPostViewModel2.setPostDate(LocalDate.of(2020, 6, 29));
        inputPostViewModel2.setPost("Hey, now I'm making a post without any comments!!");
        inputPostViewModel2.setComments(null);

        inputPost2.setPosterName("Josh");
        inputPost2.setPostDate(LocalDate.of(2020, 6, 29));
        inputPost2.setPost("Hey, now I'm making a post without any comments!!");

        outputPostViewModel2.setPostId(2);
        outputPostViewModel2.setPosterName("Josh");
        outputPostViewModel2.setPostDate(LocalDate.of(2020, 6, 29));
        outputPostViewModel2.setPost("Hey, now I'm making a post without any comments!!");
        outputPostViewModel2.setComments(null);

        outputPost2.setPostId(2);
        outputPost2.setPosterName("Josh");
        outputPost2.setPostDate(LocalDate.of(2020, 6, 29));
        outputPost2.setPost("Hey, now I'm making a post without any comments!!");

        inputPosts.add(inputPost1);
        inputPosts.add(inputPost2);
        outputPosts.add(outputPost1);
        outputPosts.add(outputPost2);

        //service = new StwitterService(commentTemplate, postClient);

        when(postClient.createPost(inputPost1)).thenReturn(outputPost1);
        when(postClient.getPostById(1)).thenReturn(outputPost1);
        when(postClient.getPostById(2)).thenReturn(outputPost2);
        when(postClient.getPosts(null)).thenReturn(outputPosts);

        when(commentClient.getComments(1)).thenReturn(outputComments);
    }

    @Test
    public void shouldCreatePostAndReturnPostViewModelAfterSendingCommentsOffToQueue() {

        var postViewModelGottenFromService = service.createPost(inputPostViewModel1);

        assertEquals(outputPostViewModel1, postViewModelGottenFromService);

    }

}
