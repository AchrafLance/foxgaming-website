package com.fox.website.services;

import com.fox.website.models.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
}
