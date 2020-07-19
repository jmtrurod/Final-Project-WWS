package com.ironhack.cityservice.controller;

import com.ironhack.cityservice.dto.CityCreate;
import com.ironhack.cityservice.dto.DescriptionDto;
import com.ironhack.cityservice.model.City;
import com.ironhack.cityservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/cities/isup")
    @ResponseStatus(HttpStatus.OK)
    public boolean isUp(){
        return true;
    }

    @PostMapping("/cities")
    @ResponseStatus(HttpStatus.CREATED)
    public City createCity(@RequestBody CityCreate cityCreate){
        return cityService.createCity(cityCreate);
    }

    @PatchMapping("/cities/content/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDescription(@PathVariable(name = "id") String id, @RequestBody DescriptionDto descriptionDto){
        cityService.updateDescription(id, descriptionDto);
    }

    @PatchMapping("/cities/pic/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePic(@PathVariable(name = "id") String id, @RequestBody byte[] pic){
        cityService.updatePic(id, pic);
    }

    @GetMapping("/cities")
    @ResponseStatus(HttpStatus.OK)
    public List<String[]> getAllCities(){
        return cityService.getAllCities();
    }
}
