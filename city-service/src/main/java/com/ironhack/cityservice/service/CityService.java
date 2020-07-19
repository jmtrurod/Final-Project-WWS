package com.ironhack.cityservice.service;

import com.ironhack.cityservice.dto.CityCreate;
import com.ironhack.cityservice.dto.DescriptionDto;
import com.ironhack.cityservice.exception.InputNotAllowed;
import com.ironhack.cityservice.model.City;
import com.ironhack.cityservice.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public City createCity(CityCreate cityCreate){
        Optional<City> user = cityRepository.findById(cityCreate.getCity() + "-" + cityCreate.getCountry());
        if (user.isPresent()){
            throw new InputNotAllowed(cityCreate.getCity() + "-" + cityCreate.getCountry() + " already exists as City");
        }
        return cityRepository.save(new City(cityCreate.getCity(), cityCreate.getCountry(), cityCreate.getPic(), cityCreate.getDescription()));
    }

    public void updateDescription(String id, DescriptionDto descriptionDto){
        City city = cityRepository.findById(id)
                .orElseThrow(()-> new InputNotAllowed(id + " doesn't exist"));
        city.setDescription(descriptionDto.getDescription());
        cityRepository.save(city);
    }

    public void updatePic(String id, byte[] pic){
        City city = cityRepository.findById(id)
                .orElseThrow(()-> new InputNotAllowed(id + " doesn't exist"));
        city.setPic(pic);
        cityRepository.save(city);
    }

    public List<String[]> getAllCities(){
        List<String[]> cities = new ArrayList<String[]>();
        cityRepository.findAll().forEach(c -> cities.add(new String[]{c.getCity(), c.getCountry()}));
        return cities;
    }
}
