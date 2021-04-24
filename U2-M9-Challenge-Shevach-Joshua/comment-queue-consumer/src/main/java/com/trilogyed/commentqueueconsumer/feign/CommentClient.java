package com.trilogyed.commentqueueconsumer.feign;

import com.trilogyed.commentqueueconsumer.util.messages.CommentEntry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@FeignClient("comment-service")
public interface CommentClient {

    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentEntry createComment(@RequestBody @Valid CommentEntry comment);

}
