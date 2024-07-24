package org.example.chatchatpractice.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatchatpractice.entity.User;
import org.example.chatchatpractice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}/profile-image")
    public ResponseEntity<Map<String, String>> getProfileImage(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        Map<String, String> response = new HashMap<>();
        response.put("profileImageUrl", user.getProfileImageUrl());
        return ResponseEntity.ok(response);
    }
}
