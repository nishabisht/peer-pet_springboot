package com.springboot.rest.petpeers.service;

import com.springboot.rest.petpeers.model.Pet;
import com.springboot.rest.petpeers.exception.ApplicationException;

import java.util.List;
import java.util.Set;

public interface IPetService {
    List<Pet> getAllPets() throws ApplicationException;

    Pet savePet(Pet pet);
}
