package com.project.BrainStorm.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TagForUser {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public TagForUser() {
    }

    public TagForUser(String name) {
        this.name = name;
    }

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
