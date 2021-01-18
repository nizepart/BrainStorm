package com.project.BrainStorm.Controllers;

import com.project.BrainStorm.Models.Post;
import com.project.BrainStorm.Models.Tag;
import com.project.BrainStorm.Models.User;
import com.project.BrainStorm.Repos.PostRepo;
import com.project.BrainStorm.Repos.TagRepo;
import com.project.BrainStorm.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private TagRepo tagRepo;

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
        if(filter != null && !filter.isEmpty()) {
            blog = postRepo.findByTitle(filter);
        } else{
            blog = postRepo.findAll();
        }
        model.addAttribute("blog", blog);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String full_text,
            @RequestParam("file") MultipartFile file,
            @RequestParam String tagName,
            Map<String, Object> model) throws IOException {
        Tag tag = new Tag(tagName);
        Post post = new Post(title, full_text, LocalDateTime.now(), user, tagRepo.findByName(tagName));
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
        tagRepo.save(tag);
        postRepo.save(post);
        return "redirect:/main";
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

        model.addAttribute("blog", blog);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "profile";
    }


    @PostMapping("/main/profile/{user}/edit")
    public String updateProfile(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam String password){
        userService.updateProfile(user);

        return "redirect:/main/profile/{user}";
    }



}
