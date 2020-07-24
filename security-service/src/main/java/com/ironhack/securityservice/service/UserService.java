package com.ironhack.securityservice.service;

import com.ironhack.securityservice.enums.Role;
import com.ironhack.securityservice.model.User;
import com.ironhack.securityservice.repository.UserRepository;
import com.ironhack.securityservice.security.CustomSecurityUser;
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

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findAllByUsername(username);
        if (user.isPresent()){
            return new CustomSecurityUser(user.get());
        }
        throw new UsernameNotFoundException("User not found");
    }

    public boolean isAdmin(User user) {
        return user.getRol().equals(Role.ADMIN);
    }

    @Transactional
    public User createUser(User user) {
        if (userRepository.findAllByUsername(user.getUsername()).isPresent()) {
            return null;
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean isAdminOrUser(User user) {
        return (user.getRol().equals(Role.USER) || user.getRol().equals(Role.ADMIN));
    }

    public boolean isAllowedUser(User user, String username) {
        if (user.getUsername().equals(username) && user.getRol().equals(Role.USER)){
            return true;
        }
        return false;
    }

    public boolean isAllowedUserOrAdmin(User user, String username) {
        if (isAllowedUser(user, username)){
            return true;
        }
        if (isAdmin(user)){
            return true;
        }
        return false;
    }
}