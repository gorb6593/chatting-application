package org.example.chatchatpractice.service;

import org.example.chatchatpractice.entity.User;
import org.example.chatchatpractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(String username) {
        return userRepository.findByUsername(username)
                .orElseGet(() -> userRepository.save(new User(username)));
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
