package com.project.BrainStorm.Repos;

import com.project.BrainStorm.Models.Post;
import com.project.BrainStorm.Models.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepo extends CrudRepository<Post, Integer> {

    List<Post> findByTitle(String title);
    List<Post> findByTag(Tag tag);

}
