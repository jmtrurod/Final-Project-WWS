package com.ironhack.edgeservice.controller;

//  import com.ironhack.edgeservice.dto.ContentDto;
import com.ironhack.edgeservice.dto.PostCreate;
import com.ironhack.edgeservice.enums.Theme;
import com.ironhack.edgeservice.model.Post;
import com.ironhack.edgeservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/posts/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody PostCreate postCreate,
                           @RequestHeader(value = "Authorization") String authorizationHeader,
                           @RequestParam(name = "username") String username){
        return postService.createPost(postCreate, authorizationHeader, username);
    }

//    @PatchMapping("/posts/content/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void updateContent(@PathVariable(name = "id") Long id, @RequestBody ContentDto contentDto,
//                          @RequestHeader(value = "Authorization") String authorizationHeader,
//                          @RequestParam(name = "username") String username){
//        postService.updateContent(id, contentDto, authorizationHeader, username);
//    }

    @PutMapping("/posts/increment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void incrementKarma(@PathVariable(name = "id") Long id, @RequestParam(name = "username") String username, @RequestHeader(value = "Authorization") String authorizationHeader){
        postService.incrementKarma(id, username, authorizationHeader);
    }

    @PutMapping("/posts/decrement/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void decrementKarma(@PathVariable(name = "id") Long id, @RequestParam(name = "username") String username, @RequestHeader(value = "Authorization") String authorizationHeader){
        postService.decrementKarma(id, username, authorizationHeader);
    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable(name = "id") Long id,
                           @RequestHeader(value = "Authorization") String authorizationHeader,
                           @RequestParam(name = "username") String username){
        postService.deletePost(id, authorizationHeader,username);
    }

    @GetMapping("/posts/city/{cityId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByCity(@PathVariable(name = "cityId") String cityId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return postService.postsByCity(cityId,authorizationHeader);
    };

    @GetMapping("/posts/city/theme")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByCityAndTheme(@RequestParam(name = "cityId") String cityId, @RequestParam(name = "theme") Theme theme, @RequestHeader(value = "Authorization") String authorizationHeader){
        return postService.postsByCityAndTheme(cityId, theme, authorizationHeader);
    };

    @GetMapping("/posts/person/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByPerson(@PathVariable(name = "username") String username, @RequestHeader(value = "Authorization") String authorizationHeader){
        return postService.postsByPerson(username, authorizationHeader);
    };

    @GetMapping("/posts/person/theme")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByPersonAndTheme(@RequestParam(name = "username") String username, @RequestParam(name = "theme") Theme theme, @RequestHeader(value = "Authorization") String authorizationHeader){
        return postService.postsByPersonAndTheme(username, theme, authorizationHeader);
    };
}
