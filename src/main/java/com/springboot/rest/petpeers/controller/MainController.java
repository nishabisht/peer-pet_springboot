package com.springboot.rest.petpeers.controller;


import com.springboot.rest.petpeers.exception.ApplicationException;
import com.springboot.rest.petpeers.model.Pet;
import com.springboot.rest.petpeers.model.User;
import com.springboot.rest.petpeers.service.IPetService;
import com.springboot.rest.petpeers.service.IUserService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/shop")
public class MainController implements IMainController {
    @Autowired
    IUserService userService;

    @Autowired          
    IPetService petService;


    public static  final Logger log = LoggerFactory.getLogger(MainController.class);
    @Override
    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
        User savedUser=null;
        savedUser=userService.saveUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @Override
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/home")
    public ResponseEntity<List<Pet>> home() throws ApplicationException {
        List<Pet> petList=petService.getAllPets();
        log.info("response body-------------------------- : "+petList.toString());
        return ResponseEntity.status(HttpStatus.OK).body(petList);
    }

    @Override
    @GetMapping("/myPets/{userId}")
    public ResponseEntity<Set<Pet>> myPets(@PathVariable  Integer userId) throws ApplicationException {
        Set<Pet> petSet=userService.getMyPets(Long.valueOf(userId));
        log.info("response of my pet ------------------------------: "+petSet.toString());
        return ResponseEntity.status(HttpStatus.OK).body(petSet);
    }


    @Override
    @PostMapping("/savePet")
    public ResponseEntity<Pet> savePet(@RequestBody @Valid Pet pet) {
        Pet savedPet=petService.savePet(pet);
        log.info("response of savePet------------------------:"+savedPet.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPet);
    }

    @Override
    @PutMapping("/buyPet/{userId}/{petId}")
    public ResponseEntity<User> buyPet(@PathVariable Integer userId,@PathVariable Integer petId) throws ApplicationException {
        User user=userService.buyPet(Long.valueOf(userId),Long.valueOf(petId));
        log.info("response of buyPet---------------------------:"+"userID : "+userId+" petId : "+petId+" user : "+user.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
