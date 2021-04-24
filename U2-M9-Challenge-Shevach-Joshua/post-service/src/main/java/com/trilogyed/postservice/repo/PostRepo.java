package com.trilogyed.postservice.repo;

import com.trilogyed.postservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findAllByPosterName(String posterName);
}
