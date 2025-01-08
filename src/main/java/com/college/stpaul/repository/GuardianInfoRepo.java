package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.stpaul.entities.GuardianInfo;

public interface GuardianInfoRepo extends JpaRepository<GuardianInfo,Long>{
    
}
