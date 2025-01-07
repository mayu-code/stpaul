package com.college.stpaul.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.stpaul.JwtSecurity.JwtProvider;
import com.college.stpaul.entities.User;
import com.college.stpaul.repository.UserRepo;
import com.college.stpaul.services.serviceInterface.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User getUserByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @Override
    public User addUser(User user) {
        return this.userRepo.save(user);
    }

    @Override
    public User getUserByJWT(String jwt) {
        String email = JwtProvider.getEmailFromToken(jwt);
        return this.userRepo.findByEmail(email);
    }
    
}
