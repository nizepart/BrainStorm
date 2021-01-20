package com.project.BrainStorm.Models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

    @Entity
    @Table(name = "usr")
    public class User implements UserDetails {

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Long id;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id.equals(user.id);
        }


        @ManyToMany
        @JoinTable(
                name = "IsLiking",
                joinColumns = {@JoinColumn(name = "liker")},
                inverseJoinColumns = {@JoinColumn(name = "post")}
        )
        private Set<Post> likedPosts = new HashSet<>();


        @ManyToMany
        @JoinTable(
                name = "IsSubbing",
                joinColumns = {@JoinColumn(name = "channel")},
                inverseJoinColumns = {@JoinColumn(name = "subscriber")}
        )
        private Set<User> subscribers = new HashSet<>();

        @ManyToMany
        @JoinTable(
                name = "IsSubbing",
                joinColumns = {@JoinColumn(name = "subscriber")},
                inverseJoinColumns = {@JoinColumn(name = "channel")}
        )
        private Set<User> subscriptions = new HashSet<>();

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        private String username;

        private String password;

        private String first_name, last_name;

        @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
        @CollectionTable(name = "IsOfRole", joinColumns = @JoinColumn(name= "user_id"))
        @Enumerated(EnumType.STRING)
        private Set<Role> roles;

        @OneToMany(mappedBy = "author", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<Post> blog;

        public Set<Post> getBlog() {
            return blog;
        }
        public boolean isAdmin(){
            return roles.contains(Role.ADMIN);
        }

        public boolean isModer() { return roles.contains(Role.MODER);}

        public void setBlog(Set<Post> blog) {
            this.blog = blog;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return getRoles();
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Set<Role> getRoles() {
            return roles;
        }

        public void setRoles(Set<Role> roles) {
            this.roles = roles;
        }

        public String getFirst_name() {
            return first_name;
        }

        public Set<Post> getLikedPosts() {
            return likedPosts;
        }

        public void setLikedPosts(Set<Post> likedPosts) {
            this.likedPosts = likedPosts;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public Set<User> getSubscribers() {
            return subscribers;
        }

        public void setSubscribers(Set<User> subscribers) {
            this.subscribers = subscribers;
        }

        public Set<User> getSubscriptions() {
            return subscriptions;
        }

        public void setSubscriptions(Set<User> subscriptions) {
            this.subscriptions = subscriptions;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }
    }

