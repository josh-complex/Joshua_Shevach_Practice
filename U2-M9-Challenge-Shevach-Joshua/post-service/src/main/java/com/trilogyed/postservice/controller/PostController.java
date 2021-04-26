package com.trilogyed.postservice.controller;

import com.trilogyed.postservice.model.Post;
import com.trilogyed.postservice.repo.PostRepo;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/post")
public class PostController {

    PostRepo repo;

    @Autowired
    public PostController(PostRepo repo) {
        this.repo = repo;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody @Valid Post post) {
        return repo.save(post);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post getPostById(@PathVariable Integer id) {
        var opPost = repo.findById(id);
        if(opPost.isPresent()) return opPost.get();
        throw new EntityNotFoundException("Could not find a post with an id of '" + id + "'");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPosts(@RequestParam(required = false) String posterName) {
        if(posterName != null) return repo.findAllByPosterName(posterName);
        return repo.findAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(@RequestBody @Valid Post post) {
        repo.getOne(post.getPostId());
        return repo.save(post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(@PathVariable Integer id) {
        repo.deleteById(id);
    }

}
