package com.college.stpaul.services.serviceInterface;

import com.college.stpaul.entities.User;

public interface UserService {
    
    User getUserByEmail(String email);
    User addUser(User user);
    User getUserByJWT(String jwt);
}
