package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.dto.BioDto;
import com.ironhack.edgeservice.dto.UserCreate;
import com.ironhack.edgeservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service")
public interface UserMicroservice {
    @GetMapping("/users/isup")
    @ResponseStatus(HttpStatus.OK)
    public boolean isUp();

    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable(name = "username") String username);

    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserCreate userCreate);

    @PatchMapping("/users/bio/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBio(@PathVariable(name = "username") String username, @RequestBody BioDto bioDto);

    @PatchMapping("/users/pic/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePic(@PathVariable(name = "username") String username, @RequestBody byte[] pic);

    @PutMapping("/users/increment/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void incrementKarma(@PathVariable(name = "username") String username);

    @PutMapping("/users/decrement/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void decrementKarma(@PathVariable(name = "username") String username);
}
