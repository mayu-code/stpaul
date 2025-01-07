package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.stpaul.entities.User;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByEmail(String email);
    
} 
