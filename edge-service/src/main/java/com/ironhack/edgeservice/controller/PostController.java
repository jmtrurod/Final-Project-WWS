package com.ironhack.edgeservice.controller;

import com.ironhack.edgeservice.dto.CityAndThemeDto;
import com.ironhack.edgeservice.dto.ContentDto;
import com.ironhack.edgeservice.dto.PersonAndThemeDto;
import com.ironhack.edgeservice.dto.PostCreate;
import com.ironhack.edgeservice.model.Post;
import com.ironhack.edgeservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/posts/isup")
    @ResponseStatus(HttpStatus.OK)
    public boolean isUp(){
        return postService.isUp();
    }

    @PostMapping("/posts/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody PostCreate postCreate){
        return postService.createPost(postCreate);
    }

    @PatchMapping("/posts/content/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBio(@PathVariable(name = "id") Long id, @RequestBody ContentDto contentDto){
        postService.updateContent(id, contentDto);
    }

    @PutMapping("/posts/increment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void incrementKarma(@PathVariable(name = "id") Long id){
        postService.incrementKarma(id);
    }

    @PutMapping("/posts/decrement/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void decrementKarma(@PathVariable(name = "username") Long id){
        postService.decrementKarma(id);
    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable(name = "id") Long id){
        postService.deletePost(id);
    }

    @GetMapping("/posts/city")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByCity(@RequestBody String cityId){
        return postService.postsByCity(cityId);
    };

    @GetMapping("/posts/city/theme")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByCityAndTheme(@RequestBody @Valid CityAndThemeDto cityAndThemeDto){
        return postService.postsByCityAndTheme(cityAndThemeDto);
    };

    @GetMapping("/posts/person")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByPerson(@RequestBody String username){
        return postService.postsByPerson(username);
    };

    @GetMapping("/posts/person/theme")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByPersonAndTheme(@RequestBody @Valid PersonAndThemeDto personAndThemeDto){
        return postService.postsByPersonAndTheme(personAndThemeDto);
    };
}
