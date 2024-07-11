package com.openclassrooms.yourcaryourway.controllers;

import com.openclassrooms.yourcaryourway.model.User;
import com.openclassrooms.yourcaryourway.request.LoginRequest;
import com.openclassrooms.yourcaryourway.response.MessageResponse;
import com.openclassrooms.yourcaryourway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.registerUser(user);
		return ResponseEntity.ok(new MessageResponse("User created"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    	User userInDb = this.userService.findUserByUsername(loginRequest.getUsername());
    	if(userInDb != null) {
    		return ResponseEntity.ok(new MessageResponse("User logged in"));
    	}
		return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error"));
    }
}
