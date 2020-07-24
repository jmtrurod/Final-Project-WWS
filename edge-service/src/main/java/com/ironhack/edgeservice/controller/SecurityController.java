package com.ironhack.edgeservice.controller;

import com.ironhack.edgeservice.client.SecurityMicroservice;
import com.ironhack.edgeservice.client.UserMicroservice;
import com.ironhack.edgeservice.exception.UserMicroserviceFail;
import com.ironhack.edgeservice.model.SecurityUser;
import com.ironhack.edgeservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Api(tags = "Security Edge Controller")
@CrossOrigin(origins = "https://front-wws.firebaseapp.com")
public class SecurityController {

    @Autowired
    private SecurityMicroservice securityMicroservice;
    @Autowired
    private UserMicroservice userMicroservice;

    @GetMapping("/users/is-admin-user")
    @ApiOperation(value="Check if is admin")
    public boolean isAdminOrUser(@RequestHeader(value = "Authorization") String authorizationHeader){
        return securityMicroservice.isAdminOrUser(authorizationHeader);
    }

    @GetMapping("/users/is-allowed-user")
    @ApiOperation(value="Check if is allowed user")
    public boolean isAllowedUser(@RequestHeader(value = "Authorization") String authorizationHeader,
                                 @RequestParam(name = "username") String username){
        return securityMicroservice.isAllowedUser(authorizationHeader, username);
    }

    @GetMapping("/users/is-admin-allowed-user")
    @ApiOperation(value="Check if is admin or allowed user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAdminOrAllowedUser(@RequestHeader(value = "Authorization") String authorizationHeader,
                                        @RequestParam(name = "username") String username){
        return securityMicroservice.isAdminOrAllowedUser(authorizationHeader, username);
    };

    @PostMapping("/users-create")
    @ApiOperation(value="Create Security user")
    @ResponseStatus(HttpStatus.CREATED)
    public SecurityUser createUser(@RequestBody SecurityUser user){
        if (userMicroservice.findById(user.getUsername())!=null){
            return securityMicroservice.createUser(user);
        }
        throw new UserMicroserviceFail("User already exists");
    }
}
