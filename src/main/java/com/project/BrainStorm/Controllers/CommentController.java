package com.project.BrainStorm.Controllers;

import com.project.BrainStorm.Models.Comment;
import com.project.BrainStorm.Models.Post;
import com.project.BrainStorm.Models.User;
import com.project.BrainStorm.Repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentRepo commentRepo;

    @GetMapping("{post}")
    public String commentList(Model model,
                              @PathVariable Post post)
    {
        Iterable<Comment> comments = commentRepo.findByPost(post);
        model.addAttribute("comments", comments);
        return "commentList.ftlh";
    }


    @PostMapping("{post}")
    public String commentSave(
            @PathVariable Post post,
            @RequestParam String full_text,
            @AuthenticationPrincipal User author){
            Comment comment = new Comment(full_text, author, post, LocalDateTime.now());
            commentRepo.save(comment);
            return "redirect:/comments/" + post.getId();
    }

}
