package com.springboot.rest.petpeers.service;

import com.springboot.rest.petpeers.converter.PetConverter;
import com.springboot.rest.petpeers.entity.PetEntity;
import com.springboot.rest.petpeers.exception.ApplicationException;
import com.springboot.rest.petpeers.model.Pet;
import com.springboot.rest.petpeers.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PetService implements IPetService{
    @Autowired
    PetRepository petRepository;


    @Override
    public List<Pet> getAllPets() throws ApplicationException {
        List<PetEntity> petList= petRepository.findAll();
        if (petList.isEmpty()){
            throw new ApplicationException("There is no pets available");
        }
        return PetConverter.convertToModel(petList);
    }

    @Override
    public Pet savePet(Pet pet) {
        PetEntity petEntity= petRepository.save(PetConverter.convertToEntity(pet));
        return PetConverter.convertToModel(petEntity);
    }
}
