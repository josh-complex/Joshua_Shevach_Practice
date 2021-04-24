package com.trilogyed.commentqueueconsumer.util.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntry {

    private Integer commentId;
    private Integer postId;
    private LocalDate createDate;
    private String commenterName;
    private String comment;

}
