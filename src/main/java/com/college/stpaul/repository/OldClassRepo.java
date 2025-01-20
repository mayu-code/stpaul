package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.stpaul.entities.OldClass;

public interface OldClassRepo extends JpaRepository<OldClass,Long>{
    
}
