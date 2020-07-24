package com.ironhack.securityservice.controller;

import com.ironhack.securityservice.model.User;
import com.ironhack.securityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserControllerImpl {

    @Autowired
    UserService userService;

    @GetMapping("/users/is-admin")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAdmin(@AuthenticationPrincipal User user) {
        return userService.isAdmin(user);
    }

    @GetMapping("/users/is-admin-user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAdminOrUser(@AuthenticationPrincipal User user) {
        return userService.isAdminOrUser(user);
    }

    @GetMapping("/users/is-admin-allowed-user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAdminOrAllowedUser(@AuthenticationPrincipal User user, @RequestParam(name = "username") String username) {
        return userService.isAllowedUserOrAdmin(user, username);
    }

    @GetMapping("/users/is-allowed-user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAllowedUser(@AuthenticationPrincipal User user, @RequestParam(name = "username") String username) {
        return userService.isAllowedUser(user, username);
    }

    @PostMapping("/users-create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}