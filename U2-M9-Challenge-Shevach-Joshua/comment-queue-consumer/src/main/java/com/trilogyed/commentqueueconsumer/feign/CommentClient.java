package com.trilogyed.commentqueueconsumer.feign;

import com.trilogyed.commentqueueconsumer.util.messages.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient("comment-service")
public interface CommentClient {

    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment updateComment(@RequestBody Comment comment, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteComment(@PathVariable Integer id);

}
