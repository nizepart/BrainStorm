package com.project.BrainStorm.Controllers;

import com.project.BrainStorm.Models.*;
import com.project.BrainStorm.Repos.PostRepo;
import com.project.BrainStorm.Repos.TagForUserRepo;
import com.project.BrainStorm.Repos.TagRepo;
import com.project.BrainStorm.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private TagRepo tagRepo;

    @Autowired
    private TagForUserRepo tagForUserRepo;

    @Autowired
    private UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model) {
        Iterable<Post> blog = postRepo.findAll();
        Iterable<Tag> tags = tagRepo.findAll();
        if(filter != null && !filter.isEmpty()) {
            blog = postRepo.findByTitle(filter);
        } else{
            blog = postRepo.findAll();
        }
        model.addAttribute("blog", blog);
        model.addAttribute("tags", tags);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main/profile/{user}")
    public String addPostInProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String link,
            @RequestParam String full_text,
            @RequestParam("file") MultipartFile file,
            @RequestParam Tag tag) throws IOException {
        Post post = new Post(title, full_text, LocalDateTime.now(), user, tag, link);
        fileSave(file, post);
        postRepo.save(post);
        return "redirect:/main/profile/" + user.getId();
    }

    @PreAuthorize("hasAuthority('MODER')")
    @GetMapping("/main/tag")
    public String updateTagList(Model model
    ){
        Iterable<Tag> tags = tagRepo.findAll();
        model.addAttribute("tags", tags);
        Iterable<TagForUser> utags = tagForUserRepo.findAll();
        model.addAttribute("utags", utags);
        return "tag";
    }


    @PreAuthorize("hasAuthority('MODER')")
    @PostMapping("/main/tag")
    public String addTag(
            @RequestParam String newTagName){
        Tag tagFromDb = tagRepo.findByName(newTagName);

        if (tagFromDb != null) {
            return "redirect:/main/tag";
        }
        if( tagForUserRepo.findByName(newTagName) != null){
            tagForUserRepo.delete(tagForUserRepo.findByName(newTagName));
        }
        Tag tag = new Tag(newTagName);
        tagRepo.save(tag);
        return "redirect:/main/tag";
    }


    @GetMapping("/main/newTag")
    public String loadTagList(Model model
    ){
        return "tagForUser";
    }


    @PostMapping("/main/newTag")
    public String updateTagList(
            @RequestParam String newTagName){
        TagForUser tag = new TagForUser(newTagName);
        if (!newTagName.isBlank())
            tagForUserRepo.save(tag);
        return "redirect:/main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String link,
            @RequestParam String full_text,
            @RequestParam("file") MultipartFile file,
            @RequestParam Tag tag) throws IOException {
        Post post = new Post(title, full_text, LocalDateTime.now(), user, tag, link);
        fileSave(file, post);
        postRepo.save(post);
        return "redirect:/main";
    }

    private void fileSave(MultipartFile file, Post post) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()){

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            post.setFilename(resultFilename);
        }
    }


    @GetMapping("/main/profile/{user}/edit")
    public String getProfile(Model model,
                             @AuthenticationPrincipal User currentUser,
                             @PathVariable User user){
        model.addAttribute("username", user.getUsername());

        return "edit";
    }

    @GetMapping("/main/profile/{user}")
    public String profilePage(Model model,
                              @AuthenticationPrincipal User currentUser,
                              @PathVariable User user){
        model.addAttribute("username", user.getUsername());
        Set<Post> blog = user.getBlog();
        Iterable<Tag> tags = tagRepo.findAll();

        model.addAttribute("tags", tags);
        model.addAttribute("blog", blog);
        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));

        return "profile";
    }


    @PostMapping("/main/profile/{user}/edit")
    public String updateProfile(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam String password,
            @RequestParam String first_name,
            @RequestParam String last_name){
        userService.updateProfile(user, password, first_name, last_name);


        return "redirect:/main/profile/" + user.getId();
    }



}
