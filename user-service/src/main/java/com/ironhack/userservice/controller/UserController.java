package com.ironhack.userservice.controller;

import com.ironhack.userservice.dto.BioDto;
import com.ironhack.userservice.dto.UserCreate;
import com.ironhack.userservice.model.User;
import com.ironhack.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/isup")
    @ResponseStatus(HttpStatus.OK)
    public boolean isUp(){
        return true;
    }

    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable(name = "username") String username){
        return userService.getUser(username);
    }

    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid UserCreate userCreate){
        return userService.createUser(userCreate);
    }

    @PatchMapping("/users/bio/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBio(@PathVariable(name = "username") String username, @RequestBody @Valid BioDto bioDto){
        userService.updateBio(username, bioDto);
    }

    @PatchMapping("/users/pic/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePic(@PathVariable(name = "username") String username, @RequestBody @NotNull byte[] pic){
        userService.updatePic(username, pic);
    }

    @PutMapping("/users/increment/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void incrementKarma(@PathVariable(name = "username") String username){
        userService.incrementKarma(username);
    }

    @PutMapping("/users/decrement/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void decrementKarma(@PathVariable(name = "username") String username){
        userService.decrementKarma(username);
    }

}
