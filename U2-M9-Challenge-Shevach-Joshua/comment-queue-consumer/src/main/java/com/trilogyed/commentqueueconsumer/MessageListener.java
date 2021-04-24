package com.trilogyed.commentqueueconsumer;

import com.trilogyed.commentqueueconsumer.feign.CommentClient;
import com.trilogyed.commentqueueconsumer.util.messages.CommentEntry;
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

    @RabbitListener(queues = CommentQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(CommentEntry msg) {
        client.createComment(msg);
    }

}
