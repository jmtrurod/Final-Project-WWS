package com.ironhack.securityservice.service;

import com.ironhack.securityservice.enums.Role;
import com.ironhack.securityservice.model.User;
import com.ironhack.securityservice.repository.UserRepository;
import com.ironhack.securityservice.security.CustomSecurityUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

@Repository
public class UserService implements UserDetailsService, Serializable {

    @Autowired
    UserRepository userRepository;

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) {
        LOGGER.info("Attempt to load User by username");
        Optional<User> user = userRepository.findAllByUsername(username);
        if (user.isPresent()){
            LOGGER.info("Found user");
            return new CustomSecurityUser(user.get());
        }
        LOGGER.warn(username + " doesnt exist");
        throw new UsernameNotFoundException("User not found");
    }

    public boolean isAdmin(User user) {
        LOGGER.info("Check if user is ADMIN");
        return user.getRol().equals(Role.ADMIN);
    }

    @Transactional
    public User createUser(User user) {
        LOGGER.info("Attempt to create User");
        if (userRepository.findAllByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("That user already exists");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean isAdminOrUser(User user) {
        LOGGER.info("Check if user is ADMIN or USER");
        return (user.getRol().equals(Role.USER) || user.getRol().equals(Role.ADMIN));
    }

    public boolean isAllowedUser(User user, String username) {
        LOGGER.info("Check if user is ALLOWED USER");
        if (user.getUsername().equals(username) && user.getRol().equals(Role.USER)){
            return true;
        }
        return false;
    }

    public boolean isAllowedUserOrAdmin(User user, String username) {
        LOGGER.info("Check if user is ADMIN or ALLOWED USER");
        if (isAllowedUser(user, username)){
            return true;
        }
        if (isAdmin(user)){
            return true;
        }
        return false;
    }
}