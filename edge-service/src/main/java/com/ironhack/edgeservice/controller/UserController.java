package com.ironhack.edgeservice.controller;
import com.ironhack.edgeservice.dto.BioDto;
import com.ironhack.edgeservice.dto.UserCreate;
import com.ironhack.edgeservice.model.User;
import com.ironhack.edgeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/isup")
    @ResponseStatus(HttpStatus.OK)
    public boolean isUp(){
        return userService.isUp();
    }

    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable(name = "username") String username){
        return userService.getUser(username);
    }

    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserCreate userCreate){
        return userService.createUser(userCreate);
    }

    @PatchMapping("/users/bio/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBio(@PathVariable(name = "username") String username, @RequestBody BioDto bioDto){
        userService.updateBio(username, bioDto);
    }

    @PatchMapping("/users/pic/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePic(@PathVariable(name = "username") String username, @RequestBody byte[] pic){
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
