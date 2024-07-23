package org.example.chatchatpractice.service;

import lombok.RequiredArgsConstructor;
import org.example.chatchatpractice.entity.User;
import org.example.chatchatpractice.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProfileService {

    private final UserRepository userRepository;

    public User updateProfile(String username, String profileImageUrl, String statusMessage, String email) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setProfileImageUrl(profileImageUrl);
        user.setStatusMessage(statusMessage);
        user.setEmail(email);

        return userRepository.save(user);
    }

    public User getProfile(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
