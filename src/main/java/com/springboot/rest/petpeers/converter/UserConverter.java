package com.springboot.rest.petpeers.converter;


import com.springboot.rest.petpeers.entity.UserEntity;
import com.springboot.rest.petpeers.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {
    public static User convertToModel(UserEntity userEntity){
        User user=new User(userEntity.getUserId(), userEntity.getUsername(), userEntity.getUserPassword(), userEntity.getConfirmPassword());
        user.setPets(PetConverter.convertToModel(userEntity.getPets()));
        return user;
    }

    public static UserEntity convertToEntity(User user){
        UserEntity userEntity=new UserEntity(user.getId(), user.getName(), user.getPassword(), user.getConfirmPassword());
        userEntity.setPets(PetConverter.convertToEntity(user.getPets()));
        return userEntity;
    }

    public static List<User> convertToModel(List<UserEntity> userEntityList){
        List<User> userList=new ArrayList<>();
        for (UserEntity user: userEntityList) {
            userList.add(convertToModel(user));
        }
        return userList;
    }

    public static List<UserEntity> convertToEntity(List<User> userList){
        List<UserEntity> userEntityList=new ArrayList<>();
        for(User user:userList){
            userEntityList.add(convertToEntity(user));
        }
        return  userEntityList;
    }



}
