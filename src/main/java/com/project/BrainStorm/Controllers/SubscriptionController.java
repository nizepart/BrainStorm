package com.project.BrainStorm.Controllers;

import com.project.BrainStorm.Models.Post;
import com.project.BrainStorm.Models.User;
import com.project.BrainStorm.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class SubscriptionController {
    @Autowired
    private UserService userService;

    @GetMapping("/main/profile/{user}/subscribe")
    public String subscribe(
            @PathVariable User user,
            @AuthenticationPrincipal User currentUser
    ){
        userService.subscribe(currentUser, user);

        return "redirect:/main/profile/"+ user.getId() ;
    }

    @GetMapping("/main/profile/{user}/unsubscribe")
    public String unsubscribe(
            @PathVariable User user,
            @AuthenticationPrincipal User currentUser
    ){
        userService.unsubscribe(currentUser, user);

        return "redirect:/main/profile/" + user.getId();
    }

    @GetMapping("/main/profile/{user}/subscriptions")
    public String usrList(
            Model model,
            @PathVariable User user,
            @AuthenticationPrincipal User currentUser) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("userChannel", user);
        model.addAttribute("users", user.getSubscriptions());

        return "subscriptions";
    }

    @GetMapping("/main/profile/{user}/subscribers")
    public String usrListSubs(
            Model model,
            @PathVariable User user,
            @AuthenticationPrincipal User currentUser) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("userChannel", user);
        model.addAttribute("users", user.getSubscribers());

        return "subscribers";
    }

    @GetMapping("/like/{post}")
    public String like(
            @PathVariable Post post,
            @AuthenticationPrincipal User currentUser){
        userService.like(currentUser, post);

        return "redirect:/main";
    }

    @GetMapping("/unlike/{post}")
    public String unlike(
            @PathVariable Post post,
            @AuthenticationPrincipal User currentUser){
        userService.unlike(currentUser, post);

        return "redirect:/main";
    }

}
