package com.project.BrainStorm.Repos;

import com.project.BrainStorm.Models.Comment;
import com.project.BrainStorm.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);

}
