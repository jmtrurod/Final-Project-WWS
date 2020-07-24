package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.SecurityUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "security-service", url = "https://wws-security-service.herokuapp.com/")
public interface SecurityMicroservice {

    @GetMapping("/users/is-admin")
    boolean isAdmin(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/users/is-admin-user")
    boolean isAdminOrUser(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/users/is-allowed-user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAllowedUser(@RequestHeader(value = "Authorization") String authorizationHeader,
                                 @RequestParam(name = "username") String username);

    @GetMapping("/users/is-admin-allowed-user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAdminOrAllowedUser(@RequestHeader(value = "Authorization") String authorizationHeader,
                                        @RequestParam(name = "username") String username);

    @PostMapping("/users-create")
    @ResponseStatus(HttpStatus.CREATED)
    public SecurityUser createUser(@RequestBody SecurityUser user);
}
