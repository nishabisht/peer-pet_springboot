package com.springboot.rest.petpeers.service;

import com.springboot.rest.petpeers.converter.PetConverter;
import com.springboot.rest.petpeers.converter.UserConverter;
import com.springboot.rest.petpeers.entity.PetEntity;
import com.springboot.rest.petpeers.entity.UserEntity;
import com.springboot.rest.petpeers.exception.ApplicationException;
import com.springboot.rest.petpeers.model.Pet;
import com.springboot.rest.petpeers.model.User;
import com.springboot.rest.petpeers.repository.PetRepository;
import com.springboot.rest.petpeers.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;


    public static final Logger log= LoggerFactory.getLogger(UserService.class);


    @Override
    @Transactional
    public User saveUser(User user) {
//        user password encoder
        UserEntity savedUser=userRepository.save(UserConverter.convertToEntity(user));
        log.info("response entity  : "+savedUser.toString());
        try {
            for (PetEntity pet : savedUser.getPets()) {
                pet.setUser(savedUser);
            }
        }catch (DataAccessException ex){
            throw ex;
        }
        savedUser.getPets().forEach(petEntity -> petRepository.save(petEntity));

        return UserConverter.convertToModel(savedUser);
    }

//    public void savedUser(User user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(UserConverter.convertToEntity(user));
//    }

    @Override
    public Set<Pet> getMyPets(Long uId) throws ApplicationException {
        UserEntity userEntity = userRepository.findById(uId).orElseThrow(()->new ApplicationException("user not found in database"));

        Set<PetEntity> petEntitySet = userEntity.getPets();
        if (petEntitySet == null||petEntitySet.isEmpty()){
           throw new ApplicationException("User don't have pet associated with him/her");
        }

        return PetConverter.convertToModel(petEntitySet);
    }

    @Transactional
    @Override
    public User buyPet(Long uId, Long pId) throws ApplicationException {
        UserEntity userEntity = userRepository.findById(uId).orElseThrow(()->new ApplicationException("No user found from id : "+ uId));
        PetEntity petEntity= petRepository.findById(pId).orElseThrow(()->new ApplicationContextException("No pet found from id : "+pId));


        if (petEntity.getUser() != null) {
            throw new ApplicationException("Pet already has an owner");

        }

        if(userEntity.getPets()==null){
            userEntity.setPets(new HashSet<>());
        }

        userEntity.getPets().add(petEntity);
        petEntity.setUser(userEntity);

        userRepository.save(userEntity);
        petRepository.save(petEntity);

        log.info("User with Id {} has bought pet with id {}",uId,pId );


        return UserConverter.convertToModel(userEntity);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
//        UserEntity user= userRepository.findByUsername(username);
//
//        if (user == null){
//            throw new UsernameNotFoundException("User is not registered with us");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getUserPassword(),new ArrayList<>());
//    }
}
