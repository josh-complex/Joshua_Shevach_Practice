package com.trilogyed.commentqueueconsumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class CommentQueueConsumerApplication {

    public static final String COMMENT_EXCHANGE = "comment-exchange";
    public static final String POST_EXCHANGE = "post-exchange";
    public static final String CREATE_COMMENT_QUEUE = "create-comment-queue";
    public static final String DELETE_COMMENT_QUEUE = "delete-comment-queue";
    public static final String UPDATE_COMMENT_QUEUE = "update-comment-queue";
    public static final String CREATE_COMMENT_ROUTE = "comment.create.#";
    public static final String DELETE_COMMENT_ROUTE = "comment.delete.#";
    public static final String UPDATE_COMMENT_ROUTE = "comment.update.#";
    public static final String DELETE_POST_QUEUE = "delete-post-queue";
    public static final String UPDATE_POST_QUEUE = "update-post-queue";
    public static final String DELETE_POST_ROUTE = "post.delete.#";
    public static final String UPDATE_POST_ROUTE = "post.update.#";

    @Bean
    public Declarables topicBindings() {
        Queue createCommentQueue = new Queue(CREATE_COMMENT_QUEUE, false);
        Queue deleteCommentQueue = new Queue(DELETE_COMMENT_QUEUE, false);
        Queue updateCommentQueue = new Queue(UPDATE_COMMENT_QUEUE, false);
        Queue deletePostQueue = new Queue(DELETE_POST_QUEUE, false);
        Queue updatePostQueue = new Queue(UPDATE_POST_QUEUE, false);

        TopicExchange commentExchange = new TopicExchange(COMMENT_EXCHANGE);
        TopicExchange postExchange = new TopicExchange(POST_EXCHANGE);

        return new Declarables(
                createCommentQueue,
                deleteCommentQueue,
                updateCommentQueue,
                deletePostQueue,
                updatePostQueue,
                commentExchange,
                postExchange,

                //Comment bindings
                BindingBuilder
                        .bind(createCommentQueue)
                        .to(commentExchange)
                        .with(CREATE_COMMENT_ROUTE),
                BindingBuilder
                        .bind(deleteCommentQueue)
                        .to(commentExchange)
                        .with(DELETE_COMMENT_ROUTE),
                BindingBuilder
                        .bind(updateCommentQueue)
                        .to(commentExchange)
                        .with(UPDATE_COMMENT_ROUTE),

                //Post bindings
                BindingBuilder
                        .bind(deletePostQueue)
                        .to(postExchange)
                        .with(DELETE_POST_ROUTE),
                BindingBuilder
                        .bind(updatePostQueue)
                        .to(postExchange)
                        .with(UPDATE_POST_ROUTE)
        );
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public static void main(String[] args) {
        SpringApplication.run(CommentQueueConsumerApplication.class, args);
    }

}
