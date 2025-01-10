package com.college.stpaul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.stpaul.entities.Subjects;

public interface SubjectsRepo extends JpaRepository<Subjects,Long>{
    
}
