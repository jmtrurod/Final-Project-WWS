package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.dto.CityCreate;
import com.ironhack.edgeservice.dto.DescriptionDto;
import com.ironhack.edgeservice.model.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "city-service")
public interface CityMicroservice {
    @GetMapping("/cities/isup")
    @ResponseStatus(HttpStatus.OK)
    public boolean isUp();

    @GetMapping("/cities/{id}")
    @ResponseStatus(HttpStatus.OK)
    public City findById(@PathVariable(name = "id") String id);

    @PostMapping("/cities")
    @ResponseStatus(HttpStatus.CREATED)
    public City createCity(@RequestBody CityCreate cityCreate);

    @PatchMapping("/cities/content/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDescription(@PathVariable(name = "id") String id, @RequestBody DescriptionDto descriptionDto);

    @PatchMapping("/cities/pic/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePic(@PathVariable(name = "id") String id, @RequestBody byte[] pic);

    @GetMapping("/cities")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllCities();
}
