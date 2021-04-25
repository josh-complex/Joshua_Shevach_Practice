package com.trilogyed.stwitterservice.service;

import com.trilogyed.stwitterservice.feign.CommentClient;
import com.trilogyed.stwitterservice.feign.PostClient;
import com.trilogyed.stwitterservice.model.Comment;
import com.trilogyed.stwitterservice.model.Post;
import com.trilogyed.stwitterservice.viewmodel.PostViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StwitterService {

    public static final String TOPIC_EXCHANGE_NAME = "comment-exchange";
    public static final String ROUTING_KEY = "comment.create.stwitter.service";

    final RabbitTemplate commentTemplate;
    PostClient postClient;
    CommentClient commentClient;

    @Autowired
    public StwitterService(RabbitTemplate commentTemplate, PostClient postClient, CommentClient commentClient) {
        this.commentTemplate = commentTemplate;
        this.postClient = postClient;
        this.commentClient = commentClient;
    }

    public PostViewModel createPost(PostViewModel postViewModel) {
        Post post = postClient.createPost(
                buildPostFromPostViewModel(postViewModel)
        );

        postViewModel.getComments().forEach(x -> x.setPostId(post.getPostId()));
        postViewModel.getComments().forEach(x -> commentTemplate.convertAndSend(TOPIC_EXCHANGE_NAME, ROUTING_KEY, x));

        return buildPostViewModelFromPostAndComment(post, commentClient.getComments(post.getPostId()));
    }

    public PostViewModel findPostById(Integer id) {
        return buildPostViewModelFromPostAndComment(postClient.getPostById(id), commentClient.getComments(id));
    }

    public List<PostViewModel> findPosts() {
        List<PostViewModel> viewModels = new ArrayList<>();
        postClient.getPosts(null).forEach(
                x -> viewModels.add(buildPostViewModelFromPostAndComment(x, commentClient.getComments(x.getPostId())))
        );
        return viewModels;
    }

    public List<PostViewModel> findPosts(String posterName) {
        return null;
    }

    public PostViewModel updatePost(PostViewModel updatePost, Integer id) {
        return null;
    }

    public void deletePost(Integer id) {

    }

    private PostViewModel buildPostViewModelFromPostAndComment(Post post, List<Comment> comments) {
        return new PostViewModel(
                post.getPostId(),
                post.getPostDate(),
                post.getPosterName(),
                post.getPost(),
                comments
        );
    }

    private Post buildPostFromPostViewModel(PostViewModel postViewModel) {
        return new Post(
                postViewModel.getPostDate(),
                postViewModel.getPosterName(),
                postViewModel.getPost()
        );
    }
}
