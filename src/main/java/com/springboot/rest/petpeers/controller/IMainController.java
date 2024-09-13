package com.springboot.rest.petpeers.controller;

import com.springboot.rest.petpeers.exception.ApplicationException;
import com.springboot.rest.petpeers.model.Pet;
import com.springboot.rest.petpeers.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

public interface IMainController {

    ResponseEntity<User> saveUser(@RequestBody User user);
    //ResponseEntity<List<Pet>> home() throws ApplicationException;
    public ResponseEntity<List<Pet>> home() throws ApplicationException;
    ResponseEntity<Set<Pet>> myPets(@PathVariable Integer userId) throws ApplicationException;
    public ResponseEntity<Pet> savePet(@RequestBody @Valid Pet pet);
    ResponseEntity<User> buyPet(@PathVariable Integer userId, @PathVariable Integer petId) throws ApplicationException;

}
