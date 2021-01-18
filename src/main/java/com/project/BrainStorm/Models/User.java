package com.project.BrainStorm.Models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

    @Entity
    @Table(name = "usr")
    public class User implements UserDetails {

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Long id;

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

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }
    }

