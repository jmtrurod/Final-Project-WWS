package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.CityMicroservice;
import com.ironhack.edgeservice.dto.CityCreate;
import com.ironhack.edgeservice.dto.DescriptionDto;
import com.ironhack.edgeservice.exception.CityMicroserviceFail;
import com.ironhack.edgeservice.model.City;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityMicroservice cityMicroservice;

    @HystrixCommand(fallbackMethod = "CityMicroserviceFail")
    public boolean isUp(){
        return cityMicroservice.isUp();
    }

    @HystrixCommand(fallbackMethod = "CityMicroserviceFail")
    public City createCity(CityCreate cityCreate){
        return cityMicroservice.createCity(cityCreate);
    }

    @HystrixCommand(fallbackMethod = "CityMicroserviceFail")
    public void updateDescription(String id, DescriptionDto descriptionDto){
        cityMicroservice.updateDescription(id, descriptionDto);
    }

    @HystrixCommand(fallbackMethod = "CityMicroserviceFail")
    public void updatePic(String id, byte[] pic){
        cityMicroservice.updatePic(id, pic);
    }

    @HystrixCommand(fallbackMethod = "CityMicroserviceFail2")
    public List<String[]> getAllCities(){
        return cityMicroservice.getAllCities();
    }

    public boolean CityMicroserviceFail(){
        return false;
    }

    public City CityMicroserviceFail(CityCreate cityCreate){
        throw new CityMicroserviceFail("Failure caught by Hystrix");
    }

    public void CityMicroserviceFail(String string, DescriptionDto descriptionDto){
        throw new CityMicroserviceFail("Failure caught by Hystrix");
    }

    public void CityMicroserviceFail(String string, byte[] bytes){
        throw new CityMicroserviceFail("Failure caught by Hystrix");
    }

    public List<String[]> CityMicroserviceFail2(){
        throw new CityMicroserviceFail("Failure caught by Hystrix");
    }
}
