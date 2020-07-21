package com.ironhack.edgeservice.controller;

import com.ironhack.edgeservice.client.SecurityMicroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SecurityController {

    @Autowired
    private SecurityMicroservice securityMicroservice;

    @GetMapping("/users/is-admin-user")
    public boolean isAdmin(@RequestHeader(value = "Authorization") String authorizationHeader){
        return securityMicroservice.isAdminOrUser(authorizationHeader);
    }
}
