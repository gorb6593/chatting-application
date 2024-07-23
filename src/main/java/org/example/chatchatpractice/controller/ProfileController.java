package org.example.chatchatpractice.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatchatpractice.entity.User;
import org.example.chatchatpractice.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        User user = profileService.getProfile(principal.getName());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User updatedUser, Principal principal) {
        profileService.updateProfile(principal.getName(),
                updatedUser.getProfileImageUrl(),
                updatedUser.getStatusMessage(),
                updatedUser.getEmail());
        return "redirect:/profile";
    }
}
