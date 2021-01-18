package com.project.BrainStorm.Controllers;

import com.project.BrainStorm.Models.User;
import com.project.BrainStorm.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/main/profile")
public class SubscriptionController {
    @Autowired
    private UserService userService;

    @GetMapping("{user}/subscribe")
    public String subscribe(
            @PathVariable User user,
            @AuthenticationPrincipal User currentUser
    ){
        userService.subscribe(currentUser, user);

        return "redirect:/main/profile/"+ user.getId() ;
    }

    @GetMapping("{user}/unsubscribe")
    public String unsubscribe(
            @PathVariable User user,
            @AuthenticationPrincipal User currentUser
    ){
        userService.unsubscribe(currentUser, user);

        return "redirect:/main/profile/" + user.getId();
    }

    @GetMapping("{user}/subscriptions")
    public String usrList(
            Model model,
            @PathVariable User user,
            @AuthenticationPrincipal User currentUser) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("userChannel", user);
        model.addAttribute("users", user.getSubscriptions());

        return "subscriptions";
    }

    @GetMapping("{user}/subscribers")
    public String usrListSubs(
            Model model,
            @PathVariable User user,
            @AuthenticationPrincipal User currentUser) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("userChannel", user);
        model.addAttribute("users", user.getSubscribers());

        return "subscribers";
    }
}
