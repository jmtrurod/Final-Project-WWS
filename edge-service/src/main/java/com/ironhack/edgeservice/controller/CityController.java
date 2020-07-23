package com.ironhack.edgeservice.controller;

import com.ironhack.edgeservice.dto.CityCreate;
import com.ironhack.edgeservice.dto.DescriptionDto;
import com.ironhack.edgeservice.model.City;
import com.ironhack.edgeservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CityController {
    @Autowired
    private CityService cityService;
//
//    @GetMapping("/cities/isup")
//    @ResponseStatus(HttpStatus.OK)
//    public boolean isUp(@RequestHeader(value = "Authorization") String authorizationHeader){
//        return cityService.isUp();
//    }

    @GetMapping("/cities/{id}")
    @ResponseStatus(HttpStatus.OK)
    public City findById(@PathVariable(name = "id") String id, @RequestHeader(value = "Authorization") String authorizationHeader){
        return cityService.findById(id, authorizationHeader);
    }

    @PostMapping("/cities")
    @ResponseStatus(HttpStatus.CREATED)
    public City createCity(@RequestBody CityCreate cityCreate, @RequestHeader(value = "Authorization") String authorizationHeader){
        return cityService.createCity(cityCreate, authorizationHeader);
    }

    @PatchMapping("/cities/content/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDescription(@PathVariable(name = "id") String id, @RequestBody DescriptionDto descriptionDto, @RequestHeader(value = "Authorization") String authorizationHeader){
        cityService.updateDescription(id, descriptionDto, authorizationHeader);
    }

    @PatchMapping("/cities/pic/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePic(@PathVariable(name = "id") String id, @RequestBody String pic, @RequestHeader(value = "Authorization") String authorizationHeader){
        cityService.updatePic(id, pic, authorizationHeader);
    }

    @GetMapping("/cities")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllCities(@RequestHeader(value = "Authorization") String authorizationHeader){
        return cityService.getAllCities(authorizationHeader);
    }
}
