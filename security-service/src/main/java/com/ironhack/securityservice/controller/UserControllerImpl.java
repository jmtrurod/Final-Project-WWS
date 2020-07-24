package com.ironhack.securityservice.controller;

import com.ironhack.securityservice.model.User;
import com.ironhack.securityservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Security Controller")
public class UserControllerImpl {

    @Autowired
    UserService userService;

    @GetMapping("/users/is-admin")
    @ApiOperation(value="Check if is admin")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAdmin(@AuthenticationPrincipal User user) {
        return userService.isAdmin(user);
    }

    @GetMapping("/users/is-admin-user")
    @ApiOperation(value="Check if is admin or user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAdminOrUser(@AuthenticationPrincipal User user) {
        return userService.isAdminOrUser(user);
    }

    @GetMapping("/users/is-admin-allowed-user")
    @ApiOperation(value="Check if is allowed user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAdminOrAllowedUser(@AuthenticationPrincipal User user, @RequestParam(name = "username") String username) {
        return userService.isAllowedUserOrAdmin(user, username);
    }

    @GetMapping("/users/is-allowed-user")
    @ApiOperation(value="Check if is admin or allowed user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAllowedUser(@AuthenticationPrincipal User user, @RequestParam(name = "username") String username) {
        return userService.isAllowedUser(user, username);
    }

    @PostMapping("/users-create")
    @ApiOperation(value="Create user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}