package com.trilogyed.commentqueueconsumer.feign;

import com.trilogyed.commentqueueconsumer.util.messages.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient("comment-service")
@RequestMapping("/comment")
public interface CommentClient {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Comment createComment(@RequestBody Comment comment);

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    Comment updateComment(@RequestBody Comment comment);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteComment(@PathVariable Integer id);

}
