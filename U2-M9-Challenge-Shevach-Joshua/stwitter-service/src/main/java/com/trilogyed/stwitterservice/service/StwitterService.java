package com.trilogyed.stwitterservice.service;

import com.trilogyed.stwitterservice.exception.NotFoundException;
import com.trilogyed.stwitterservice.feign.CommentClient;
import com.trilogyed.stwitterservice.feign.PostClient;
import com.trilogyed.stwitterservice.model.Comment;
import com.trilogyed.stwitterservice.model.Post;
import com.trilogyed.stwitterservice.viewmodel.PostViewModel;
import lombok.var;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StwitterService {

    final String COMMENT_EXCHANGE = "comment-exchange";
    final String POST_EXCHANGE = "post-exchange";
    final String CREATE_COMMENT_ROUTE = "comment.create.stwitter.service";
    final String DELETE_COMMENT_ROUTE = "comment.delete.stwitter.service";
    final String UPDATE_COMMENT_ROUTE = "comment.update.stwitter.service";
    final String DELETE_POST_ROUTE = "post.delete.stwitter.service";
    final String UPDATE_POST_ROUTE = "post.update.stwitter.service";

    final RabbitTemplate rabbitTemplate;
    PostClient postClient;
    CommentClient commentClient;

    @Autowired
    public StwitterService(RabbitTemplate rabbitTemplate, PostClient postClient, CommentClient commentClient) {
        this.rabbitTemplate = rabbitTemplate;
        this.postClient = postClient;
        this.commentClient = commentClient;
    }

    @Transactional
    public PostViewModel createPost(PostViewModel postViewModel) {
        Post post = postClient.createPost(
                buildPostFromPostViewModel(postViewModel)
        );

        postViewModel.getComments().forEach(x -> x.setPostId(post.getPostId()));
        postViewModel.getComments().forEach(x -> rabbitTemplate.convertAndSend(COMMENT_EXCHANGE, CREATE_COMMENT_ROUTE, x));

        return buildPostViewModelFromPostAndComment(post, commentClient.getComments(post.getPostId()));
    }

    @Transactional
    public PostViewModel findPostById(Integer id) {
        var post = postClient.getPostById(id);
        if (post == null) throw new NotFoundException("Could not find post with an id of '" + id + "'");

        return buildPostViewModelFromPostAndComment(
                post,
                commentClient.getComments(id)
        );
    }

    @Transactional
    public List<PostViewModel> findPosts(String posterName) {
        return postClient.getPosts(posterName).stream()
                .map(x -> buildPostViewModelFromPostAndComment(
                        x,
                        commentClient.getComments(x.getPostId())
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public PostViewModel updatePost(PostViewModel updatePost) {
        var post = postClient.getPostById(updatePost.getPostId());
        if(post == null)
            throw new NotFoundException("Could not find post with an id of '" + updatePost.getPostId() + "'");

        rabbitTemplate.convertAndSend(POST_EXCHANGE, UPDATE_POST_ROUTE, buildPostFromPostViewModel(updatePost));

        return buildPostViewModelFromPostAndComment(
                post,
                commentClient.getComments(updatePost.getPostId())
        );
    }

    @Transactional
    public void deletePost(Integer id) {
        deleteAllComments(id);
        rabbitTemplate.convertAndSend(POST_EXCHANGE, DELETE_POST_ROUTE, id);
    }

    /*
    I figured that I'd pass the postId through this because if we're commenting on
    a post in a frontend environment, we'd obviously have eyes on the post's id already
    since we'd build the page elements using the post view model information
     */
    @Transactional
    public PostViewModel createComment(Comment comment) {
        var post = postClient.getPostById(comment.getPostId());
        if (post == null) throw new NotFoundException("Could not find post with an id of '" + comment.getPostId() + "'");

        comment.setPostId(comment.getPostId());

        rabbitTemplate.convertAndSend(COMMENT_EXCHANGE, CREATE_COMMENT_ROUTE, comment);

        return buildPostViewModelFromPostAndComment(
                post,
                commentClient.getComments(comment.getPostId())
        );
    }

    @Transactional
    public PostViewModel updateComment(Comment updateComment) {
        if (commentClient.getCommentById(updateComment.getCommentId()) == null)
            throw new NotFoundException("Could not find comment with an id of '" + updateComment.getCommentId() + "'");

        var post = postClient.getPostById(updateComment.getPostId());
        if (post == null)
            throw new NotFoundException("Could not find post with an id of '" + updateComment.getPostId() + "'");

        rabbitTemplate.convertAndSend(COMMENT_EXCHANGE, UPDATE_COMMENT_ROUTE, updateComment);
        return buildPostViewModelFromPostAndComment(
                post,
                commentClient.getComments(updateComment.getPostId())
        );
    }

    @Transactional
    public PostViewModel deleteComment(Integer id) {
        var comment = commentClient.getCommentById(id);
        if (comment == null)
            throw new NotFoundException("Could not find comment with an id of '" + id + "'");

        var post = postClient.getPostById(comment.getPostId());
        if (post == null)
            throw new NotFoundException("Could not find post with an id of '" + comment.getPostId() + "'");

        rabbitTemplate.convertAndSend(COMMENT_EXCHANGE, DELETE_COMMENT_ROUTE, id);

        return buildPostViewModelFromPostAndComment(
                post,
                commentClient.getComments(post.getPostId())
        );
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
                postViewModel.getPostId(),
                postViewModel.getPostDate(),
                postViewModel.getPosterName(),
                postViewModel.getPost()
        );
    }

    private void deleteAllComments(Integer postId) {
        commentClient.getComments(postId).forEach(
                x -> rabbitTemplate.convertAndSend(COMMENT_EXCHANGE, DELETE_COMMENT_ROUTE, x.getCommentId())
        );
    }
}
