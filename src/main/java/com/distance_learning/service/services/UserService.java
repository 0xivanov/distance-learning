package com.distance_learning.service.services;

import com.distance_learning.service.models.UserServiceRegisterModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void registerUser(UserServiceRegisterModel model);
    void loginUser();
}
