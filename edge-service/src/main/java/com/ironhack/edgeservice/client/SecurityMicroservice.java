package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.SecurityUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "security-service")
public interface SecurityMicroservice {

    @GetMapping("/users/is-admin")
    boolean isAdmin(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/users/is-admin-user")
    boolean isAdminOrUser(@RequestHeader(value = "Authorization") String authorizationHeader);

    @GetMapping("/users/is-allowed-user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAllowedUser(@RequestHeader(value = "Authorization") String authorizationHeader,
                                 @RequestParam(name = "username") String username);
}
