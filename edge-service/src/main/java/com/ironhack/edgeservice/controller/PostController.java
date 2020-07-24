package com.ironhack.edgeservice.controller;

//  import com.ironhack.edgeservice.dto.ContentDto;
import com.ironhack.edgeservice.dto.PostCreate;
import com.ironhack.edgeservice.enums.Theme;
import com.ironhack.edgeservice.model.Post;
import com.ironhack.edgeservice.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Api(tags = "Post Edge Controller")
@CrossOrigin(origins = "https://front-wws.firebaseapp.com")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/posts/create")
    @ApiOperation(value="Create post")
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
    @ApiOperation(value="Increment post karma")
    @ResponseStatus(HttpStatus.OK)
    public void incrementKarma(@PathVariable(name = "id") Long id, @RequestParam(name = "username") String username, @RequestHeader(value = "Authorization") String authorizationHeader){
        postService.incrementKarma(id, username, authorizationHeader);
    }

    @PutMapping("/posts/decrement/{id}")
    @ApiOperation(value="Decrement post karma")
    @ResponseStatus(HttpStatus.OK)
    public void decrementKarma(@PathVariable(name = "id") Long id, @RequestParam(name = "username") String username, @RequestHeader(value = "Authorization") String authorizationHeader){
        postService.decrementKarma(id, username, authorizationHeader);
    }

    @DeleteMapping("/posts/{id}")
    @ApiOperation(value="Delete psot by id")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable(name = "id") Long id,
                           @RequestHeader(value = "Authorization") String authorizationHeader,
                           @RequestParam(name = "username") String username){
        postService.deletePost(id, authorizationHeader,username);
    }

    @GetMapping("/posts/city/{cityId}")
    @ApiOperation(value="Filter posts by city")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByCity(@PathVariable(name = "cityId") String cityId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return postService.postsByCity(cityId,authorizationHeader);
    };

    @GetMapping("/posts/city/theme")
    @ApiOperation(value="Filter posts by city and theme")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByCityAndTheme(@RequestParam(name = "cityId") String cityId, @RequestParam(name = "theme") Theme theme, @RequestHeader(value = "Authorization") String authorizationHeader){
        return postService.postsByCityAndTheme(cityId, theme, authorizationHeader);
    };

    @GetMapping("/posts/person/{username}")
    @ApiOperation(value="Filter posts by person")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByPerson(@PathVariable(name = "username") String username, @RequestHeader(value = "Authorization") String authorizationHeader){
        return postService.postsByPerson(username, authorizationHeader);
    };

    @GetMapping("/posts/person/theme")
    @ApiOperation(value="Filter posts by person and theme")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByPersonAndTheme(@RequestParam(name = "username") String username, @RequestParam(name = "theme") Theme theme, @RequestHeader(value = "Authorization") String authorizationHeader){
        return postService.postsByPersonAndTheme(username, theme, authorizationHeader);
    };
}
