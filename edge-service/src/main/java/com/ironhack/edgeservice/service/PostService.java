package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.PostMicroservice;
import com.ironhack.edgeservice.dto.CityAndThemeDto;
import com.ironhack.edgeservice.dto.ContentDto;
import com.ironhack.edgeservice.dto.PersonAndThemeDto;
import com.ironhack.edgeservice.dto.PostCreate;
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

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public boolean isUp(){
        return postMicroservice.isUp();
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public Post createPost(PostCreate postCreate){
        return postMicroservice.createPost(postCreate);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public void updateContent(Long id, ContentDto contentDto){
        postMicroservice.updateContent(id, contentDto);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public void incrementKarma(Long id){
        postMicroservice.incrementKarma(id);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public void decrementKarma(Long id){
        postMicroservice.decrementKarma(id);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public void deletePost(Long id){
        postMicroservice.deletePost(id);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public List<Post> postsByCity(String cityId){
        return postMicroservice.postsByCity(cityId);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public List<Post> postsByCityAndTheme(CityAndThemeDto cityAndThemeDto){
        return postMicroservice.postsByCityAndTheme(cityAndThemeDto);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public List<Post> postsByPerson(String username){
        return postMicroservice.postsByPerson(username);
    }

    @HystrixCommand(fallbackMethod = "PostMicroserviceFail")
    public List<Post> postsByPersonAndTheme(PersonAndThemeDto personAndThemeDto){
        return postMicroservice.postsByPersonAndTheme(personAndThemeDto);
    }

    public boolean UserMicroserviceFail(){
        return false;
    }

    public Post UserMicroserviceFail(PostCreate postCreate){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public void UserMicroserviceFail(Long id, ContentDto contentDto){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public void UserMicroserviceFail(Long id){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public List<Post> UserMicroserviceFail(String string){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public List<Post>  UserMicroserviceFail(CityAndThemeDto cityAndThemeDto){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }

    public void UserMicroserviceFail(PersonAndThemeDto personAndThemeDto){
        throw new UserMicroserviceFail("Failure caught by Hystrix");
    }
}
