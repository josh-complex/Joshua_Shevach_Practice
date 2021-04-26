package com.trilogyed.commentqueueconsumer.feign;

import com.trilogyed.commentqueueconsumer.util.messages.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient("post-service")
@RequestMapping("/post")
public interface PostClient {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Post createPost(@RequestBody Post post);

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    Post updatePost(@RequestBody Post post);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deletePost(@PathVariable Integer id);

}
