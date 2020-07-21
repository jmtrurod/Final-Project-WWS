package com.ironhack.edgeservice.service;
import com.ironhack.edgeservice.client.SecurityMicroservice;
import com.ironhack.edgeservice.client.UserMicroservice;
import com.ironhack.edgeservice.dto.BioDto;
import com.ironhack.edgeservice.dto.UserCreate;
import com.ironhack.edgeservice.exception.UserMicroserviceFail;
import com.ironhack.edgeservice.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserMicroservice userMicroservice;
    @Autowired
    private SecurityMicroservice securityMicroservice;

//    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
//    public boolean isUp(){
//        return userMicroservice.isUp();
//    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail2")
    public User getUser(String username, String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        return userMicroservice.findById(username);
    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
    public User createUser(UserCreate userCreate, String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        return userMicroservice.createUser(userCreate);
    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
    public void updateBio(String username, BioDto bioDto, String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        userMicroservice.updateBio(username, bioDto);
    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
    public void updatePic(String username, byte[] pic, String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        userMicroservice.updatePic(username, pic);
    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
    public void incrementKarma(String username, String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        userMicroservice.incrementKarma(username);
    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
    public void decrementKarma(String username, String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        userMicroservice.decrementKarma(username);
    }

    public void UserMicroserviceFail(String string, String string2){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public void UserMicroserviceFail(String username, byte[] bits, String string2){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public void UserMicroserviceFail(String username, BioDto bioDto, String string2){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public User UserMicroserviceFail(UserCreate userCreate, String string2){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public User UserMicroserviceFail2(String string, String string2){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public boolean UserMicroserviceFail(String string2){
        return false;
    }
}
