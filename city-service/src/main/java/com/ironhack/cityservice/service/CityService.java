package com.ironhack.cityservice.service;

import com.ironhack.cityservice.dto.CityCreate;
//import com.ironhack.cityservice.dto.DescriptionDto;
import com.ironhack.cityservice.exception.InputNotAllowed;
import com.ironhack.cityservice.model.City;
import com.ironhack.cityservice.repository.CityRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    private static final Logger LOGGER = LogManager.getLogger(CityService.class);

    public City createCity(CityCreate cityCreate){
        LOGGER.info("Attempt to create city");
        Optional<City> user = cityRepository.findById(cityCreate.getCity() + "-" + cityCreate.getCountry());
        if (user.isPresent()){
            throw new InputNotAllowed(cityCreate.getCity() + "-" + cityCreate.getCountry() + " already exists as City");
        }
        return cityRepository.save(new City(cityCreate.getCity(), cityCreate.getCountry(), cityCreate.getPic(), cityCreate.getDescription()));
    }

    public City findById(String cityId){
        LOGGER.info("Attempt find city by id");
        return cityRepository.findById(cityId)
                .orElseThrow(()-> new InputNotAllowed(cityId + " doesn't exist"));
    }

//    public void updateDescription(String id, DescriptionDto descriptionDto){
//        City city = cityRepository.findById(id)
//                .orElseThrow(()-> new InputNotAllowed(id + " doesn't exist"));
//        city.setDescription(descriptionDto.getDescription());
//        cityRepository.save(city);
//    }
//
//    public void updatePic(String id, String pic){
//        City city = cityRepository.findById(id)
//                .orElseThrow(()-> new InputNotAllowed(id + " doesn't exist"));
//        city.setPic(pic);
//        cityRepository.save(city);
//    }

    public List<String> getAllCities(){
        LOGGER.info("Getting all cities as string");
        List<String> cities = new ArrayList<String>();
        cityRepository.findAll().forEach(c -> cities.add(c.getId()));
        return cities;
    }

    public List<City> getAllCitiesObj(){
        LOGGER.info("Getting all cities");
        return cityRepository.findAll();
    }
}
