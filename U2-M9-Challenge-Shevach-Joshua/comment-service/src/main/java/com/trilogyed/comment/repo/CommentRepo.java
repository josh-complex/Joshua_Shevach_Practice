package com.trilogyed.comment.repo;

import com.trilogyed.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByPostId(Integer id);

}
