package com.trilogyed.post.controller;

import com.trilogyed.post.model.Post;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class PostController {

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public Post createPost(@RequestBody Post post) {
        return null;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable int id) {
        return null;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    public void updatePost(@RequestBody Post post, @PathVariable int id) {

    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public void deletePost(@PathVariable int id) {

    }
}
