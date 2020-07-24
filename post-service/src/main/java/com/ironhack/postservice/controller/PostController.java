package com.ironhack.postservice.controller;

//   import com.ironhack.postservice.dto.ContentDto;
import com.ironhack.postservice.dto.PostCreate;
import com.ironhack.postservice.enums.Theme;
import com.ironhack.postservice.model.Post;
import com.ironhack.postservice.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/")
@Api(tags = "Post Controller")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/posts/create")
    @ApiOperation(value="Create a post")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody @Valid PostCreate postCreate){
        return postService.createPost(postCreate);
    }

//    @PatchMapping("/posts/content/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void updateContent(@PathVariable(name = "id") Long id, @RequestBody @Valid ContentDto contentDto){
//        postService.updateContent(id, contentDto);
//    }

    @PutMapping("/posts/increment/{id}")
    @ApiOperation(value="Increment post karma")
    @ResponseStatus(HttpStatus.OK)
    public void incrementKarma(@PathVariable(name = "id") Long id, @RequestParam(name = "username") String username){
        postService.incrementKarma(id, username);
    }

    @PutMapping("/posts/decrement/{id}")
    @ApiOperation(value="Decrement post karma")
    @ResponseStatus(HttpStatus.OK)
    public void decrementKarma(@PathVariable(name = "id") Long id, @RequestParam(name = "username") String username){
        postService.decrementKarma(id, username);
    }

    @DeleteMapping("/posts/{id}")
    @ApiOperation(value="Delete post")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable(name = "id") Long id){
        postService.deletePost(id);
    }

    @GetMapping("/posts/city/{cityId}")
    @ApiOperation(value="Filter posts by city")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByCity(@PathVariable(name = "cityId") @NotNull String cityId){
        return postService.postsByCity(cityId);
    }

    @GetMapping("/posts/city/theme")
    @ApiOperation(value="Filter posts by city and theme")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByCityAndTheme(@RequestParam(name = "cityId") String cityId, @RequestParam(name = "theme") Theme theme){
        return postService.postsByCityAndTheme(cityId, theme);
    }

    @GetMapping("/posts/person/{username}")
    @ApiOperation(value="Filter posts by person")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByPerson(@PathVariable(name = "username") @NotNull String username){
        return postService.postsByPerson(username);
    }

    @GetMapping("/posts/person/theme")
    @ApiOperation(value="Filter posts by person and theme")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByPersonAndTheme(@RequestParam(name = "username") String username, @RequestParam(name = "theme") Theme theme){
        return postService.postsByPersonAndTheme(username, theme);
    }
}
