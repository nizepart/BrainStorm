package com.project.BrainStorm.Repos;

import com.project.BrainStorm.Models.TagForUser;
import org.springframework.data.repository.CrudRepository;

public interface TagForUserRepo extends CrudRepository<TagForUser, Integer> {
    TagForUser findByName(String name);
}
