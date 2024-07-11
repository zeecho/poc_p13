package com.openclassrooms.yourcaryourway.service;

import com.openclassrooms.yourcaryourway.model.User;
import com.openclassrooms.yourcaryourway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
