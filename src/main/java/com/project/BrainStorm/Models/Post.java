package com.project.BrainStorm.Models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String title;

    private String link;

    private String full_text;

    private LocalDateTime creationDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IsOfTag")
    private Tag tag;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "post"
    )
    private Set<Comment> comments;

    public Set<Comment> getComment() {
        return comments;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getCreationDate() {
        return creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setComment(Set<Comment> comment) {
        this.comments = comment;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    private String filename;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post() {
    }

    public Post(String title, String full_text, LocalDateTime creationDate, User author, Tag tag, String link) {
        this.title = title;
        this.full_text = full_text;
        this.creationDate = creationDate;
        this.author = author;
        this.tag = tag;
        this.link = link;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : " ";
    }

    public String getTagName() {
        return tag != null ? tag.getName() : " ";
    }

    public Integer getTagId() {
        return tag != null ? tag.getId() : null;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFull_text() {
        return full_text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
