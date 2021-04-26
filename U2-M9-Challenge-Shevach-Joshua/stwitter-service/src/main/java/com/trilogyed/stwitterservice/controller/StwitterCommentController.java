package com.trilogyed.stwitterservice.controller;

import com.trilogyed.stwitterservice.model.Comment;
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
@RequestMapping("/stwitter/comment")
public class StwitterCommentController {

    StwitterService service;

    @Autowired
    public StwitterCommentController(StwitterService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostViewModel createComment(@RequestBody @Valid Comment comment) {
        return service.createComment(comment);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PostViewModel updateComment(@RequestBody Comment comment) {
        return service.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PostViewModel deleteComment(@PathVariable Integer id) {
        return service.deleteComment(id);
    }

}
