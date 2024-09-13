package com.springboot.rest.petpeers.converter;

import com.springboot.rest.petpeers.entity.PetEntity;
import com.springboot.rest.petpeers.model.Pet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PetConverter {

    public static Pet convertToModel(PetEntity petEntity){
        Pet petModel=new Pet();

        petModel.setId(petEntity.getPetId());
        petModel.setName(petEntity.getPetName());
        petModel.setAge(petEntity.getPetAge());
        petModel.setPlace(petEntity.getPetPlace());
        //petModel.setUser(UserConverter.convertToModel(petEntity.getUser()));
        return petModel;
    }

    public static List<Pet> convertToModel(List<PetEntity> petEntityList){
        List<Pet> pModelList=new ArrayList<>();
        if (petEntityList!=null){
            for (PetEntity pet:petEntityList) {
                pModelList.add(convertToModel(pet));
            }
         return  pModelList;
        }
        return pModelList;
    }

    public static PetEntity convertToEntity(Pet pet){
        PetEntity petEntity=new PetEntity();

        petEntity.setPetId(pet.getId());
        petEntity.setPetName(pet.getName());
        petEntity.setPetAge(pet.getAge());
        petEntity.setPetPlace(pet.getPlace());
       // petEntity.setUser(UserConverter.convertToEntity(pet.getUser()));
        return petEntity;
    }


    public static List<PetEntity> convertToEntity(List<Pet> petList){
        List<PetEntity> petEntityList=new ArrayList<>();
        if (petList!=null){
            for (Pet pet:petList) {
                petEntityList.add(convertToEntity(pet));
            }
            return  petEntityList;
        }
        return petEntityList;
    }

    public static Set<PetEntity> convertToEntity(Set<Pet> petSet){
        Set<PetEntity> petEntitySet=new HashSet<>();
        if (petSet!=null){
            for (Pet pet:petSet) {
                petEntitySet.add(convertToEntity(pet));
            }
            return  petEntitySet;
        }
        return petEntitySet;
    }

    public static Set<Pet> convertToModel(Set<PetEntity> petEntitySet){
        Set<Pet> petSet=new HashSet<>();
        if (petEntitySet!=null){
            for (PetEntity pet:petEntitySet) {
                petSet.add(convertToModel(pet));
            }
            return  petSet;
        }
        return petSet;
    }

}
