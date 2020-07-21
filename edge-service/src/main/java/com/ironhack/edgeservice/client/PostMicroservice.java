package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.dto.ContentDto;
import com.ironhack.edgeservice.dto.PostCreate;
import com.ironhack.edgeservice.enums.Theme;
import com.ironhack.edgeservice.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "post-service")
public interface PostMicroservice {
    @GetMapping("/posts/isup")
    @ResponseStatus(HttpStatus.OK)
    public boolean isUp();

    @PostMapping("/posts/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody PostCreate postCreate);

    @PatchMapping("/posts/content/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateContent(@PathVariable(name = "id") Long id, @RequestBody ContentDto contentDto);

    @PutMapping("/posts/increment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void incrementKarma(@PathVariable(name = "id") Long id, @RequestParam(name = "username") String username);

    @PutMapping("/posts/decrement/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void decrementKarma(@PathVariable(name = "id") Long id, @RequestParam(name = "username") String username);

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable(name = "id") Long id);

    @GetMapping("/posts/city/{cityId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByCity(@PathVariable(name = "cityId") String cityId);

    @GetMapping("/posts/city/theme")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByCityAndTheme(@RequestParam(name = "cityId") String cityId, @RequestParam(name = "theme") Theme theme);

    @GetMapping("/posts/person/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByPerson(@PathVariable(name = "username") String username);

    @GetMapping("/posts/person/theme")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByPersonAndTheme(@RequestParam(name = "username") String username, @RequestParam(name = "theme") Theme theme);
}
