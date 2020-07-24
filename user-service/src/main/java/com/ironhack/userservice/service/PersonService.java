package com.ironhack.userservice.service;

// import com.ironhack.userservice.dto.BioDto;
import com.ironhack.userservice.dto.PersonCreate;
import com.ironhack.userservice.exception.InputNotAllowed;
import com.ironhack.userservice.model.Person;
import com.ironhack.userservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository userRepository;

    public Person getUser(String username){
        System.out.println(username);
        Person userU = userRepository.findById(username).orElseThrow(() -> new InputNotAllowed(username + " is not an existing user"));
        System.out.println(userU.getName());
        return userU;
    }

    public List<Person> findAll(){
        return userRepository.findAll();
    }

    public Person createUser(PersonCreate personCreate){
        Optional<Person> user = userRepository.findById(personCreate.getUsername());
        if (user.isPresent()){
            throw new InputNotAllowed(personCreate.getUsername() + " already exists as username");
        }
        return userRepository.save(new Person(personCreate.getUsername(), personCreate.getName(), personCreate.getBio(), personCreate.getPic(), personCreate.getMail()));
    }

//    public void updateBio(String username, BioDto bioDto){
//        User user = userRepository.findById(username).orElseThrow(() -> new InputNotAllowed(username + " is not an existing user"));
//        user.setBio(bioDto.getBio());
//        userRepository.save(user);
//    }
//
//    public void updatePic(String username, String pic){
//        User user = userRepository.findById(username).orElseThrow(() -> new InputNotAllowed(username + " is not an existing user"));
//        user.setPic(pic);
//        userRepository.save(user);
//    }

//    public void incrementKarma(String username){
//        User user = userRepository.findById(username).orElseThrow(() -> new InputNotAllowed(username + " is not an existing user"));
//        user.setKarma(user.getKarma()+1);
//        userRepository.save(user);
//    }
//
//    public void decrementKarma(String username){
//        User user = userRepository.findById(username).orElseThrow(() -> new InputNotAllowed(username + " is not an existing user"));
//        user.setKarma(user.getKarma()-1);
//        userRepository.save(user);
//    }
}
