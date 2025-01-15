package com.icwd.user.service.services;

import com.icwd.user.service.entities.User;

import java.util.List;

public interface UserService {

    //create user
    User createUser(User user);

    //get all the users
    List<User> getAllUsers();

    //get user with user id
    User getUserById(String userId);
}
