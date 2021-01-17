package com.project.BrainStorm.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tag"
    )
    private Set<Post> blog;

    public Set<Post> getBlog() {
        return blog;
    }

    public void setBlog(Set<Post> blog) {
        this.blog = blog;
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
