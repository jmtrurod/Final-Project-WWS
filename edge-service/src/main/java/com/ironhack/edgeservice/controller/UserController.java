package com.ironhack.edgeservice.controller;
import com.ironhack.edgeservice.dto.BioDto;
import com.ironhack.edgeservice.dto.UserCreate;
import com.ironhack.edgeservice.model.User;
import com.ironhack.edgeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping("/users/isup")
//    @ResponseStatus(HttpStatus.OK)
//    public boolean isUp(){
//        return userService.isUp();
//    }

    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable(name = "username") String username, @RequestHeader(value = "Authorization") String authorizationHeader){
        return userService.getUser(username, authorizationHeader);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll(@RequestHeader(value = "Authorization") String authorizationHeader){
        return userService.findAll(authorizationHeader);
    };

    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserCreate userCreate){
        return userService.createUser(userCreate);
    }

    @PatchMapping("/users/bio/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBio(@PathVariable(name = "username") String username, @RequestBody BioDto bioDto, @RequestHeader(value = "Authorization") String authorizationHeader){
        userService.updateBio(username, bioDto, authorizationHeader);
    }

    @PatchMapping("/users/pic/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePic(@PathVariable(name = "username") String username, @RequestBody String pic, @RequestHeader(value = "Authorization") String authorizationHeader){
        userService.updatePic(username, pic, authorizationHeader);
    }

    @PutMapping("/users/increment/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void incrementKarma(@PathVariable(name = "username") String username, @RequestHeader(value = "Authorization") String authorizationHeader){
        userService.incrementKarma(username, authorizationHeader);
    }

    @PutMapping("/users/decrement/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void decrementKarma(@PathVariable(name = "username") String username, @RequestHeader(value = "Authorization") String authorizationHeader){
        userService.decrementKarma(username, authorizationHeader);
    }

}
