package com.example.demo.Service;

import com.example.demo.Entities.User;
import com.example.demo.Repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);
    UserRepo userRepo;
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String signUp(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("Client");
        userRepo.save(user);
        log.info("User signup successful with username : {}" , user.getUsername());

        return user.getUsername();
    }
}
