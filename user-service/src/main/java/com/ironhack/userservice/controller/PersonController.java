package com.ironhack.userservice.controller;

//  import com.ironhack.userservice.dto.BioDto;
import com.ironhack.userservice.dto.PersonCreate;
import com.ironhack.userservice.model.Person;
import com.ironhack.userservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Person findById(@PathVariable(name = "username") String username){
        System.out.println("HEEEE");
        return personService.getUser(username);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findAll(){
        return personService.findAll();
    }

    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createUser(@RequestBody @Valid PersonCreate userCreate){
        return personService.createUser(userCreate);
    }

//    @PatchMapping("/users/bio/{username}")
//    @ResponseStatus(HttpStatus.OK)
//    public void updateBio(@PathVariable(name = "username") String username, @RequestBody @Valid BioDto bioDto){
//        userService.updateBio(username, bioDto);
//    }
//
//    @PatchMapping("/users/pic/{username}")
//    @ResponseStatus(HttpStatus.OK)
//    public void updatePic(@PathVariable(name = "username") String username, @RequestBody @NotNull String pic){
//        userService.updatePic(username, pic);
//    }
//
//    @PutMapping("/users/increment/{username}")
//    @ResponseStatus(HttpStatus.OK)
//    public void incrementKarma(@PathVariable(name = "username") String username){
//        userService.incrementKarma(username);
//    }
//
//    @PutMapping("/users/decrement/{username}")
//    @ResponseStatus(HttpStatus.OK)
//    public void decrementKarma(@PathVariable(name = "username") String username){
//        userService.decrementKarma(username);
//    }

}
