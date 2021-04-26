package com.trilogyed.commentqueueconsumer;

import com.trilogyed.commentqueueconsumer.feign.CommentClient;
import com.trilogyed.commentqueueconsumer.feign.PostClient;
import com.trilogyed.commentqueueconsumer.util.messages.Comment;
import com.trilogyed.commentqueueconsumer.util.messages.Post;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    CommentClient commentClient;
    PostClient postClient;

    @Autowired
    public MessageListener(CommentClient commentClient, PostClient postClient) {
        this.commentClient = commentClient;
        this.postClient = postClient;
    }

    //Comment queue listeners
    @RabbitListener(queues = CommentQueueConsumerApplication.CREATE_COMMENT_QUEUE)
    public void receiveCreateCommentMessage(Comment comment) {
        commentClient.createComment(comment);
    }

    @RabbitListener(queues = CommentQueueConsumerApplication.UPDATE_COMMENT_QUEUE)
    public void receiveUpdateCommentMessage(Comment comment) {
        commentClient.updateComment(comment);
    }

    @RabbitListener(queues = CommentQueueConsumerApplication.DELETE_COMMENT_QUEUE)
    public void receiveDeleteCommentMessage(Integer id) {
        commentClient.deleteComment(id);
    }

    //Post queue listeners
    @RabbitListener(queues = CommentQueueConsumerApplication.UPDATE_POST_QUEUE)
    public void receiveUpdatePostMessage(Post post) {
        postClient.updatePost(post);
    }

    @RabbitListener(queues = CommentQueueConsumerApplication.DELETE_POST_QUEUE)
    public void receiveDeletePostMessage(Integer id) {
        postClient.deletePost(id);
    }
}
