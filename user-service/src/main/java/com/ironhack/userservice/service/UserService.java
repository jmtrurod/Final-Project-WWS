package com.ironhack.userservice.service;

import com.ironhack.userservice.dto.BioDto;
import com.ironhack.userservice.dto.UserCreate;
import com.ironhack.userservice.exception.InputNotAllowed;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(String username){
        User user = userRepository.findById(username).orElseThrow(() -> new InputNotAllowed(username + " is not an existing user"));
        return user;
    }

    public User createUser(UserCreate userCreate){
        Optional<User> user = userRepository.findById(userCreate.getUsername());
        if (user.isPresent()){
            throw new InputNotAllowed(userCreate.getUsername() + " already exists as username");
        }
        return userRepository.save(new User(userCreate.getUsername(), userCreate.getName(), userCreate.getBio(), userCreate.getPic(), userCreate.getMail()));
    }

    public void updateBio(String username, BioDto bioDto){
        User user = userRepository.findById(username).orElseThrow(() -> new InputNotAllowed(username + " is not an existing user"));
        user.setBio(bioDto.getBio());
        userRepository.save(user);
    }

    public void updatePic(String username, byte[] pic){
        User user = userRepository.findById(username).orElseThrow(() -> new InputNotAllowed(username + " is not an existing user"));
        user.setPic(pic);
        userRepository.save(user);
    }

    public void incrementKarma(String username){
        User user = userRepository.findById(username).orElseThrow(() -> new InputNotAllowed(username + " is not an existing user"));
        user.setKarma(user.getKarma()+1);
        userRepository.save(user);
    }

    public void decrementKarma(String username){
        User user = userRepository.findById(username).orElseThrow(() -> new InputNotAllowed(username + " is not an existing user"));
        user.setKarma(user.getKarma()-1);
        userRepository.save(user);
    }
}
