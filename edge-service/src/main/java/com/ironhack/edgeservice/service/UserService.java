package com.ironhack.edgeservice.service;
import com.ironhack.edgeservice.client.SecurityMicroservice;
import com.ironhack.edgeservice.client.UserMicroservice;
//  import com.ironhack.edgeservice.dto.BioDto;
import com.ironhack.edgeservice.dto.UserCreate;
import com.ironhack.edgeservice.exception.UserMicroserviceFail;
import com.ironhack.edgeservice.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMicroservice userMicroservice;
    @Autowired
    private SecurityMicroservice securityMicroservice;

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail2")
    public User getUser(String username, String authorizationHeader){
        LOGGER.info("Attempt to get user by id");
        securityMicroservice.isAdminOrUser(authorizationHeader);
        LOGGER.info("Allowed user");
        return userMicroservice.findById(username);
    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail2")
    public List<User> findAll(String authorizationHeader){
        LOGGER.info("Attempt to get all users");
        securityMicroservice.isAdminOrUser(authorizationHeader);
        LOGGER.info("Allowed user");
        return userMicroservice.findAll();
    }

    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
    public User createUser(UserCreate userCreate) {
        LOGGER.info("Attempt to create user");
        UserCreate newUserCreate = new UserCreate(
                userCreate.getUsername(),
                userCreate.getName(),
                userCreate.getBio(),
                userCreate.getPic(),
                userCreate.getMail()
        );
        return userMicroservice.createUser(userCreate);
    }

//    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
//    public void updateBio(String username, BioDto bioDto, String authorizationHeader){
//        securityMicroservice.isAdminOrUser(authorizationHeader);
//        userMicroservice.updateBio(username, bioDto);
//    }
//
//    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
//    public void updatePic(String username, String pic, String authorizationHeader){
//        securityMicroservice.isAdminOrUser(authorizationHeader);
//        userMicroservice.updatePic(username, pic);
//    }
//
//    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
//    public void incrementKarma(String username, String authorizationHeader){
//        securityMicroservice.isAdminOrUser(authorizationHeader);
//        userMicroservice.incrementKarma(username);
//    }
//
//    @HystrixCommand(fallbackMethod = "UserMicroserviceFail")
//    public void decrementKarma(String username, String authorizationHeader){
//        securityMicroservice.isAdminOrUser(authorizationHeader);
//        userMicroservice.decrementKarma(username);
//    }

    public void UserMicroserviceFail(String string, String string2){
        LOGGER.info("Hystrix failure");
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public List<User> UserMicroserviceFail2(String authorizationHeader){
        LOGGER.info("Hystrix failure");
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public void UserMicroserviceFail(String username, String bits, String string2){
        LOGGER.info("Hystrix failure");
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

//    public void UserMicroserviceFail(String username, BioDto bioDto, String string2){
//        throw new UserMicroserviceFail("Failure caught by Hystrix");
//    }

    public User UserMicroserviceFail(UserCreate userCreate){
        LOGGER.info("Hystrix failure");
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public User UserMicroserviceFail2(String string, String string2){
        LOGGER.info("Hystrix failure");
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }
}
