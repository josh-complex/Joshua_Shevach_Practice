package com.trilogyed.stwitterservice.feign;

import com.trilogyed.stwitterservice.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "post-service")
@RequestMapping("/post")
public interface PostClient {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Post createPost(@RequestBody Post post);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Post getPostById(@PathVariable Integer id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Post> getPosts(@RequestParam(required = false) String posterName);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Post updatePost(@RequestBody Post post, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deletePost(@PathVariable Integer id);

}
