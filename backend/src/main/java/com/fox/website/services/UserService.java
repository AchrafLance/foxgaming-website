package com.fox.website.services;

import com.fox.website.models.User;

import java.util.List;

public interface UserService {

    User register(User user);
    List<User> findAllUsers();
}
