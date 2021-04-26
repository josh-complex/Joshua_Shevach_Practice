package com.trilogyed.comment.controller;

import com.trilogyed.comment.model.Comment;
import com.trilogyed.comment.repo.CommentRepo;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/comment")
@CacheConfig(cacheNames = {"comment"})
public class CommentController {

    private final CommentRepo repo;

    @Autowired
    public CommentController(CommentRepo repo) {
        this.repo = repo;
    }

    @CachePut(key = "#result.getCommentId()")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody @Valid Comment comment) {
        return repo.save(comment);
    }


    @Cacheable
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment getCommentById(@PathVariable Integer id) {
        var opComment = repo.findById(id);
        if(opComment.isPresent()) return opComment.get();
        throw new EntityNotFoundException("Could not find a comment with an id of '" + id + "'");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getComments(@RequestParam(required = false) Integer postId) {
        if(postId != null) return repo.findAllByPostId(postId);
        return repo.findAll();
    }

    @CacheEvict(key = "#comment.getCommentId()")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Comment updateComment(@RequestBody @Valid Comment comment) {
        repo.getOne(comment.getCommentId());
        return repo.save(comment);
    }

    @CacheEvict
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteComment(@PathVariable Integer id) {
        repo.deleteById(id);
    }


}
