package com.trilogyed.stwitterservice.controller;

import com.trilogyed.stwitterservice.model.Comment;
import com.trilogyed.stwitterservice.model.Post;
import com.trilogyed.stwitterservice.service.StwitterService;
import com.trilogyed.stwitterservice.viewmodel.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/stwitter/post")
public class StwitterPostController {

    StwitterService service;

    @Autowired
    public StwitterPostController(StwitterService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostViewModel createPost(@RequestBody @Valid PostViewModel postViewModel) {
        return service.createPost(postViewModel);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostViewModel getPostByPostId(@PathVariable Integer id) {
        return service.findPostById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostViewModel> getPosts(@RequestParam(required = false) String posterName) {
        return service.findPosts(posterName);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PostViewModel updatePost(@RequestBody PostViewModel postViewModel) {
        return service.updatePost(postViewModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(@PathVariable Integer id) {
        service.deletePost(id);
    }

}
