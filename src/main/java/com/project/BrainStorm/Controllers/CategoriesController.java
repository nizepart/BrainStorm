package com.project.BrainStorm.Controllers;

import com.project.BrainStorm.Models.Post;
import com.project.BrainStorm.Models.Tag;
import com.project.BrainStorm.Repos.PostRepo;
import com.project.BrainStorm.Repos.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoriesController {

    @Autowired
    private TagRepo tagRepo;

    @Autowired
    private PostRepo postRepo;

    @GetMapping("/category/{tag}")
    public String Category(@PathVariable Tag tag,
                                 Model model){
        Iterable<Post> blog = postRepo.findByTag(tag);
        model.addAttribute("blog", blog);
        return "categories";
    }

    @GetMapping("/category")
    public String CategoriesList(Model model){
        Iterable<Tag> tags = tagRepo.findAll();
        model.addAttribute("tags", tags);
        return "AllCategories";
    }
}
