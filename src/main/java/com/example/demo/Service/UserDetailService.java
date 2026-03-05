package com.example.demo.Service;

import com.example.demo.Entities.User;
import com.example.demo.Repository.UserRepo;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepo userRepository;

    UserDetailService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->  new UsernameNotFoundException(username));

        return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();

    }
}