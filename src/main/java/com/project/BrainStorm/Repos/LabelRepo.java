package com.project.BrainStorm.Repos;

import com.project.BrainStorm.Models.Label;
import org.springframework.data.repository.CrudRepository;

public interface LabelRepo extends CrudRepository<Label, Integer> {
    Label findByName(String name);
}
