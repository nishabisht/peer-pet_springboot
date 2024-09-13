package com.springboot.rest.petpeers.service;

import com.springboot.rest.petpeers.exception.ApplicationException;
import com.springboot.rest.petpeers.model.Pet;
import com.springboot.rest.petpeers.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;

public interface IUserService {

    User saveUser(User user);
    Set<Pet> getMyPets(Long uId) throws ApplicationException;
    User buyPet(Long uId, Long pId) throws ApplicationException;

}
