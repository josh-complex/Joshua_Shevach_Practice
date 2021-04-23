package com.trilogyed.comment.controller;

import com.trilogyed.comment.model.Comment;
import com.trilogyed.comment.repo.CommentRepo;
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
@RequestMapping("/comment")
public class CommentController {

    private final CommentRepo repo;

    @Autowired
    public CommentController(CommentRepo repo) {
        this.repo = repo;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody @Valid Comment comment) {
        return repo.save(comment);
    }

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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Comment updateComment(@RequestBody @Valid Comment comment, @PathVariable Integer id) {
        var opComment = repo.findById(id);
        if(opComment.isPresent()) return repo.save(comment);
        throw new EntityNotFoundException("Could not find a comment with an id of '" + id + "'");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Integer id) {
        repo.deleteById(id);
    }


}
