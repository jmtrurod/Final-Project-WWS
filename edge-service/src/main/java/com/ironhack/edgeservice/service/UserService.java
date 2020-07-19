package com.ironhack.edgeservice.service;
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

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
    public boolean isUp(){
        return userMicroservice.isUp();
    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail2")
    public User getUser(String username){
        return userMicroservice.findById(username);
    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
    public User createUser(UserCreate userCreate){
        return userMicroservice.createUser(userCreate);
    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
    public void updateBio(String username, BioDto bioDto){
        userMicroservice.updateBio(username, bioDto);
    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
    public void updatePic(String username, byte[] pic){
        userMicroservice.updatePic(username, pic);
    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
    public void incrementKarma(String username){
        userMicroservice.incrementKarma(username);
    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
    public void decrementKarma(String username){
        userMicroservice.decrementKarma(username);
    }

    public void UserMicroserviceFail(String string){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public void UserMicroserviceFail(String username, byte[] bits){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public void UserMicroserviceFail(String username, BioDto bioDto){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public User UserMicroserviceFail(UserCreate userCreate){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public User UserMicroserviceFail2(String string){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public boolean UserMicroserviceFail(){
        return false;
    }
}
