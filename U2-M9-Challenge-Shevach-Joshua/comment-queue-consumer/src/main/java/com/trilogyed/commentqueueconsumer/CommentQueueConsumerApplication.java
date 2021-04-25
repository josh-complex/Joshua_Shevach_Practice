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

    public static final String TOPIC_EXCHANGE_NAME = "comment-exchange";
    public static final String CREATE_QUEUE = "create-comment-queue";
    public static final String DELETE_QUEUE = "delete-comment-queue";
    public static final String UPDATE_QUEUE = "update-comment-queue";
    public static final String CREATE_ROUTE = "comment.create.#";
    public static final String DELETE_ROUTE = "comment.delete.#";
    public static final String UPDATE_ROUTE = "comment.update.#";

    /*@Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }
*/
    @Bean
    public Declarables topicBindings() {
        Queue createQueue = new Queue(CREATE_QUEUE, false);
        Queue deleteQueue = new Queue(DELETE_QUEUE, false);
        Queue updateQueue = new Queue(UPDATE_QUEUE, false);

        TopicExchange topicExchange = new TopicExchange(TOPIC_EXCHANGE_NAME);

        return new Declarables(
                createQueue,
                deleteQueue,
                updateQueue,
                topicExchange,
                BindingBuilder
                        .bind(createQueue)
                        .to(topicExchange)
                        .with(CREATE_ROUTE),
                BindingBuilder
                        .bind(deleteQueue)
                        .to(topicExchange)
                        .with(DELETE_ROUTE),
                BindingBuilder
                        .bind(updateQueue)
                        .to(topicExchange)
                        .with(UPDATE_ROUTE)
        );
    }

	/*@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		BindingBuilder builder = new BindingBuilder();
		return BindingBuilder.bind(queue).to(exchange).with(CREATE_ROUTE);
	}*/

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public static void main(String[] args) {
        SpringApplication.run(CommentQueueConsumerApplication.class, args);
    }

}
