package com.trilogyed.stwitterservice.feign;

import com.trilogyed.stwitterservice.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "comment-service")
@RequestMapping("/comment")
public interface CommentClient {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Comment getCommentById(@PathVariable Integer id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Comment> getComments(@RequestParam(required = false) Integer postId);

}
