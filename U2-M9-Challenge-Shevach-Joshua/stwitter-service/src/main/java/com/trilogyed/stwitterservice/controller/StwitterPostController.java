package com.trilogyed.stwitterservice.controller;

import com.trilogyed.stwitterservice.model.Comment;
import com.trilogyed.stwitterservice.model.Post;
import com.trilogyed.stwitterservice.service.StwitterService;
import com.trilogyed.stwitterservice.viewmodel.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/stwitter/post")
@CacheConfig(cacheNames = {"post"})
public class StwitterPostController {

    StwitterService service;

    @Autowired
    public StwitterPostController(StwitterService service) {
        this.service = service;
    }

    @CachePut(key = "#result.getPostId()")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostViewModel createPost(@RequestBody @Valid PostViewModel postViewModel) {
        System.out.println("CREATING POST");
        return service.createPost(postViewModel);
    }

    @Cacheable
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostViewModel getPostByPostId(@PathVariable Integer id) {
        System.out.println("GETTING POST WITH ID '" + id + "'");
        return service.findPostById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostViewModel> getPosts(@RequestParam(required = false) String posterName) {
        System.out.println("GETTING ALL POSTS");
        return service.findPosts(posterName);
    }

    @CacheEvict(key = "#postViewModel.getPostId()")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PostViewModel updatePost(@RequestBody PostViewModel postViewModel) {
        System.out.println("UPDATING POST " + postViewModel);
        return service.updatePost(postViewModel);
    }

    @CacheEvict
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(@PathVariable Integer id) {
        System.out.println("DELETING POST WITH ID '" + id + "'");
        service.deletePost(id);
    }

}
