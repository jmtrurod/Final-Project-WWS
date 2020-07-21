package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.PostMicroservice;
import com.ironhack.edgeservice.client.SecurityMicroservice;
import com.ironhack.edgeservice.dto.ContentDto;
import com.ironhack.edgeservice.dto.PostCreate;
import com.ironhack.edgeservice.enums.Theme;
import com.ironhack.edgeservice.exception.UserMicroserviceFail;
import com.ironhack.edgeservice.model.Post;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMicroservice postMicroservice;
    @Autowired
    private SecurityMicroservice securityMicroservice;

//    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
//    public boolean isUp(){
//        return postMicroservice.isUp();
//    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public Post createPost(PostCreate postCreate, String authorizationHeader, String username){
        securityMicroservice.isAllowedUser(authorizationHeader, username);
        return postMicroservice.createPost(postCreate);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public void updateContent(Long id, ContentDto contentDto, String authorizationHeader, String username){
        securityMicroservice.isAllowedUser(authorizationHeader, username);
        postMicroservice.updateContent(id, contentDto);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public void incrementKarma(Long id, String username, String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        postMicroservice.incrementKarma(id, username);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public void decrementKarma(Long id, String username, String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        postMicroservice.decrementKarma(id, username);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public void deletePost(Long id, String authorizationHeader, String username){
        try {
            securityMicroservice.isAllowedUser(authorizationHeader, username);
        }
        catch (Exception e){
            securityMicroservice.isAdmin(authorizationHeader);
        }
        postMicroservice.deletePost(id);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public List<Post> postsByCity(String cityId, String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        return postMicroservice.postsByCity(cityId);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public List<Post> postsByCityAndTheme(String cityId, Theme theme, String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        return postMicroservice.postsByCityAndTheme(cityId, theme);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public List<Post> postsByPerson(String username, String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        return postMicroservice.postsByPerson(username);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public List<Post> postsByPersonAndTheme(String username, Theme theme, String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        return postMicroservice.postsByPersonAndTheme(username, theme);
    }

    public boolean PostMicroserviceFail(String string2){
        return false;
    }

    public Post PostMicroserviceFail(PostCreate postCreate, String string, String string2){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public void PostMicroserviceFail(Long id, ContentDto contentDto,String string, String string2){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public void PostMicroserviceFail(Long id, String string, String string2){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public void PostMicroserviceFail(Long id, String string){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public List<Post> PostMicroserviceFail(String string, String string2){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public List<Post> PostMicroserviceFail(String string, Theme theme, String string2){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }
}
