package com.project.BrainStorm.Service;

import com.project.BrainStorm.Models.Post;
import com.project.BrainStorm.Models.Role;
import com.project.BrainStorm.Models.User;
import com.project.BrainStorm.Repos.PostRepo;
import com.project.BrainStorm.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(String username, String first_name, String last_name, User user, Map<String, String> form) {
        user.setUsername(username);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
    }

    public void updateProfile(User user, String password, String first_name, String last_name) {
        if (!StringUtils.isEmpty(password) && !StringUtils.isEmpty(first_name) && !StringUtils.isEmpty(last_name)) {
            user.setPassword(passwordEncoder.encode(password));
            user.setFirst_name(first_name);
            user.setLast_name(last_name);}
        

        userRepo.save(user);
    }

    public void subscribe(User currentUser, User user) {
        user.getSubscribers().add(currentUser);
        userRepo.save(user);
    }

    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().remove(currentUser);
        userRepo.save(user);
    }

    public void like(User currentUser, Post post) {
        post.getLikers().add(currentUser);
        postRepo.save(post);
    }

    public void unlike(User currentUser, Post post) {
        post.getLikers().remove(currentUser);
        postRepo.save(post);
    }
}
