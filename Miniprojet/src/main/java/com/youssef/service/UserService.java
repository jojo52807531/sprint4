package com.youssef.service;

import com.youssef.dto.RegistrationDto;
import com.youssef.entities.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);


    User findUserByUsername(String username);
}
