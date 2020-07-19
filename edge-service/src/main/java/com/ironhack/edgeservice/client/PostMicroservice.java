package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.dto.CityAndThemeDto;
import com.ironhack.edgeservice.dto.ContentDto;
import com.ironhack.edgeservice.dto.PersonAndThemeDto;
import com.ironhack.edgeservice.dto.PostCreate;
import com.ironhack.edgeservice.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public void incrementKarma(@PathVariable(name = "id") Long id);

    @PutMapping("/posts/decrement/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void decrementKarma(@PathVariable(name = "username") Long id);

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable(name = "id") Long id);

    @GetMapping("/posts/city")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByCity(@RequestBody String cityId);

    @GetMapping("/posts/city/theme")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByCityAndTheme(@RequestBody @Valid CityAndThemeDto cityAndThemeDto);

    @GetMapping("/posts/person")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByPerson(@RequestBody String username);

    @GetMapping("/posts/person/theme")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> postsByPersonAndTheme(@RequestBody @Valid PersonAndThemeDto personAndThemeDto);
}
