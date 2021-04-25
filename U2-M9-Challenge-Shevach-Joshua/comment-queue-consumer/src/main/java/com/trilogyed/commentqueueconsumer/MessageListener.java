package com.trilogyed.commentqueueconsumer;

import com.trilogyed.commentqueueconsumer.feign.CommentClient;
import com.trilogyed.commentqueueconsumer.util.messages.Comment;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    CommentClient client;

    @Autowired
    public MessageListener(CommentClient client) {
        this.client = client;
    }

    @RabbitListener(queues = CommentQueueConsumerApplication.CREATE_QUEUE)
    public void receiveCreateMessage(Comment comment) {
        client.createComment(comment);
    }
/*
    @RabbitListener(queues = CommentQueueConsumerApplication.UPDATE_QUEUE)
    public void receiveUpdateMessage(Comment comment) {
        client.updateComment(comment, comment.getCommentId());
    }*/

    @RabbitListener(queues = CommentQueueConsumerApplication.DELETE_QUEUE)
    public void receiveMessage(Integer id) { client.deleteComment(id); }
}
