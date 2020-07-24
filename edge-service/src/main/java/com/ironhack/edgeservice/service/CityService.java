package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.CityMicroservice;
import com.ironhack.edgeservice.client.SecurityMicroservice;
import com.ironhack.edgeservice.dto.CityCreate;
//import com.ironhack.edgeservice.dto.DescriptionDto;
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
    @Autowired
    private SecurityMicroservice securityMicroservice;

    @HystrixCommand(fallbackMethod = "CityMicroserviceFail")
    public City findById(String cityId, String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        return cityMicroservice.findById(cityId);
    }

    @HystrixCommand(fallbackMethod = "CityMicroserviceFail")
    public City createCity(CityCreate cityCreate, String authorizationHeader){
        securityMicroservice.isAdmin(authorizationHeader);
        return cityMicroservice.createCity(cityCreate);
    }

//    @HystrixCommand(fallbackMethod = "CityMicroserviceFail")
//    public void updateDescription(String id, DescriptionDto descriptionDto, String authorizationHeader){
//        securityMicroservice.isAdmin(authorizationHeader);
//        cityMicroservice.updateDescription(id, descriptionDto);
//    }
//
//    @HystrixCommand(fallbackMethod = "CityMicroserviceFail")
//    public void updatePic(String id, String pic, String authorizationHeader){
//        securityMicroservice.isAdmin(authorizationHeader);
//        cityMicroservice.updatePic(id, pic);
//    }

    @HystrixCommand(fallbackMethod = "CityMicroserviceFail2")
    public List<String> getAllCities(String authorizationHeader){
        securityMicroservice.isAdminOrUser(authorizationHeader);
        return cityMicroservice.getAllCities();
    }

    @HystrixCommand(fallbackMethod = "CityMicroserviceFail3")
    public List<City> getAllCitiesObj(String authorizationHeader) {
        securityMicroservice.isAdminOrUser(authorizationHeader);
        return cityMicroservice.getAllCitiesObj();
    }

    public City CityMicroserviceFail(CityCreate cityCreate, String string){
        throw new CityMicroserviceFail("Failure caught by Hystrix");
    }

//    public void CityMicroserviceFail(String string, DescriptionDto descriptionDto, String string2){
//        throw new CityMicroserviceFail("Failure caught by Hystrix");
//    }

    public void CityMicroserviceFail(String string, String bytes, String string2){
        throw new CityMicroserviceFail("Failure caught by Hystrix");
    }

    public List<String> CityMicroserviceFail2(String string){
        throw new CityMicroserviceFail("Failure caught by Hystrix");
    }

    public List<City> CityMicroserviceFail3(String authorizationHeader){
        throw new CityMicroserviceFail("Failure caught by Hystrix");
    }

    public City CityMicroserviceFail(String cityId, String string){
        throw new CityMicroserviceFail("Failure caught by Hystrix");
    }
}
