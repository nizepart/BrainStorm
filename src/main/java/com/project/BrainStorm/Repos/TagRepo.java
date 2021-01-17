package com.project.BrainStorm.Repos;

import com.project.BrainStorm.Models.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepo extends CrudRepository<Tag, Integer> {
    Tag findByName(String tagName);
}
