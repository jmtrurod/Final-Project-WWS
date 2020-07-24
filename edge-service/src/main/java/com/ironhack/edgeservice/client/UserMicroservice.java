package com.ironhack.edgeservice.client;

//  import com.ironhack.edgeservice.dto.BioDto;
import com.ironhack.edgeservice.dto.UserCreate;
import com.ironhack.edgeservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service", url = "https://wws-user-service.herokuapp.com/")
public interface UserMicroservice {

    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable(name = "username") String username);

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll();

    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserCreate userCreate);

//    @PatchMapping("/users/bio/{username}")
//    @ResponseStatus(HttpStatus.OK)
//    public void updateBio(@PathVariable(name = "username") String username, @RequestBody BioDto bioDto);
//
//    @PatchMapping("/users/pic/{username}")
//    @ResponseStatus(HttpStatus.OK)
//    public void updatePic(@PathVariable(name = "username") String username, @RequestBody String pic);

//    @PutMapping("/users/increment/{username}")
//    @ResponseStatus(HttpStatus.OK)
//    public void incrementKarma(@PathVariable(name = "username") String username);
//
//    @PutMapping("/users/decrement/{username}")
//    @ResponseStatus(HttpStatus.OK)
//    public void decrementKarma(@PathVariable(name = "username") String username);
}
