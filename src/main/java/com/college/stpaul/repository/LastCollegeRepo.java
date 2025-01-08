package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.stpaul.entities.LastCollege;

public interface LastCollegeRepo extends JpaRepository<LastCollege,Long>{
    
}
