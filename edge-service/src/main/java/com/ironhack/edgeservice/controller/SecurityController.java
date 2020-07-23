package com.ironhack.edgeservice.controller;

import com.ironhack.edgeservice.client.SecurityMicroservice;
import com.ironhack.edgeservice.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SecurityController {

    @Autowired
    private SecurityMicroservice securityMicroservice;

    @GetMapping("/users/is-admin-user")
    public boolean isAdminOrUser(@RequestHeader(value = "Authorization") String authorizationHeader){
        return securityMicroservice.isAdminOrUser(authorizationHeader);
    }

    @GetMapping("/users/is-allowed-user")
    public boolean isAllowedUser(@RequestHeader(value = "Authorization") String authorizationHeader,
                                 @RequestParam(name = "username") String username){
        return securityMicroservice.isAllowedUser(authorizationHeader, username);
    }

    @GetMapping("/users/is-admin-allowed-user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAdminOrAllowedUser(@RequestHeader(value = "Authorization") String authorizationHeader,
                                        @RequestParam(name = "username") String username){
        return securityMicroservice.isAdminOrAllowedUser(authorizationHeader, username);
    };

    @PostMapping("/users-create")
    @ResponseStatus(HttpStatus.CREATED)
    public SecurityUser createUser(@RequestBody SecurityUser user){
        return securityMicroservice.createUser(user);
    }
}
